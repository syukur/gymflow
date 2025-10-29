package com.lylastudio.gymflow.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private List<ErrorDetail> errors;
    private LocalDateTime timestamp;
    private String requestId;

    @Getter
    @Setter
    @Builder
    public static class ErrorDetail {
        private String field;
        private String error;
    }
}
