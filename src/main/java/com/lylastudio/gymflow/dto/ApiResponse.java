package com.lylastudio.gymflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ApiResponse<T> {

    @Schema(description = "API response status, true if success, false if failed")
    private boolean success;

    @Schema(description = "Description of the response")
    private String message;

    @Schema(description = "API response data")
    private T data;

    @Schema(description = "List of error, if any error")
    private List<ErrorDetail> errors;

    @Schema(description = "Receiving timestamp")
    private LocalDateTime timestamp;

    @Schema(description = "Id of request, every request are tag by id")
    private String requestId;

    @Getter
    @Setter
    @Builder
    public static class ErrorDetail {
        private String field;
        private String error;
    }
}
