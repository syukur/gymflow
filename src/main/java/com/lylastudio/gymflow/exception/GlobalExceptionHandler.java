package com.lylastudio.gymflow.exception;

import com.lylastudio.gymflow.dto.ApiResponse;
import com.lylastudio.gymflow.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ApiResponseUtil responseUtil;

    public GlobalExceptionHandler(ApiResponseUtil responseUtil) {
        this.responseUtil = responseUtil;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseUtil.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        List<ApiResponse.ErrorDetail> errorDetails = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::mapFieldError)
                .collect(Collectors.toList());

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .success(false)
                .message("Validasi data gagal")
                .errors(errorDetails)
                .timestamp(java.time.LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    private ApiResponse.ErrorDetail mapFieldError(FieldError error) {
        return ApiResponse.ErrorDetail.builder()
                .field(error.getField())
                .error(error.getDefaultMessage())
                .build();
    }
}
