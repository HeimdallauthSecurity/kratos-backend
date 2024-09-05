package com.heimdallauth.auth.kratosbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFound(ResourceNotFound e, WebRequest request) {
        HttpStatus httpResponseStatus = HttpStatus.BAD_REQUEST;
        HttpStatus payloadStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(httpResponseStatus).body(buildErrorDTO(e,request,payloadStatus));
    }

    private ErrorDTO buildErrorDTO(ResourceNotFound exception, WebRequest request, HttpStatus status) {
        return ErrorDTO.builder()
                .timestamp(Instant.now())
                .error(exception.getMessage())
                .status(status.value())
                .path(request.getContextPath())
                .debugInfo(exception.incidentId)
                .build();
    }
}
