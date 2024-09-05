package com.heimdallauth.auth.kratosbackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDTO {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String debugInfo;
}