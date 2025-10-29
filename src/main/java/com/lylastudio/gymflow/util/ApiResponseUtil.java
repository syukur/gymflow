package com.lylastudio.gymflow.util;

import com.lylastudio.gymflow.dto.ApiResponse;
import org.slf4j.MDC;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ApiResponseUtil {

    private final MessageSource messageSource;

    private static final String MDC_KEY = "requestId";

    public ApiResponseUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private String localize(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    public  <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(localize(message))
                .data(data)
                .timestamp(LocalDateTime.now())
                .requestId(MDC.get(MDC_KEY))
                .build();
    }

    public  ApiResponse<Void> success(String message) {
        return ApiResponse.<Void>builder()
                .success(true)
                .message(localize(message))
                .timestamp(LocalDateTime.now())
                .requestId(MDC.get(MDC_KEY))
                .build();
    }

    public ApiResponse<?> error(String message) {
        return ApiResponse.builder()
                .success(false)
                .message(localize(message))
                .timestamp(LocalDateTime.now())
                .requestId(MDC.get(MDC_KEY))
                .errors(List.of(
                        ApiResponse.ErrorDetail.builder()
                                .field(null)
                                .error(message)
                                .build()
                ))
                .build();
    }

    public ApiResponse<?> error(String message, String field, String errorDetail) {
        return ApiResponse.builder()
                .success(false)
                .message(message)
                .timestamp(LocalDateTime.now())
                .requestId(MDC.get(MDC_KEY))
                .errors(List.of(
                        ApiResponse.ErrorDetail.builder()
                                .field(field)
                                .error(errorDetail)
                                .build()
                ))
                .build();
    }
}
