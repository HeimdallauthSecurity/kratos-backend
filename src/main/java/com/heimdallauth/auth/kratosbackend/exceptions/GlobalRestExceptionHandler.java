package com.heimdallauth.auth.kratosbackend.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFound(ResourceNotFound e, WebRequest request) {
        HttpStatus httpResponseStatus = HttpStatus.BAD_REQUEST;
        HttpStatus payloadStatus = HttpStatus.NOT_FOUND;
        return handleExceptionInternal(buildErrorDTO(e, request, payloadStatus), httpResponseStatus, request);
    }

    private ResponseEntity<ErrorDTO> handleExceptionInternal(ErrorDTO errorDTO, HttpStatus responseStatus, WebRequest request) {
        return ResponseEntity.status(responseStatus).body(errorDTO);
    }

    private ErrorDTO buildErrorDTO(ResourceNotFound exception, WebRequest request, HttpStatus status) {
        MDC.put("incidentId", exception.incidentId);
        log.error(Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining("|")));
        MDC.clear();
        return ErrorDTO.builder()
                .timestamp(Instant.now())
                .error(exception.getMessage())
                .status(status.value())
                .path(request.getContextPath())
                .debugInfo(MessageFormat.format("Incident Id: {0}", exception.incidentId))
                .build();
    }
}
