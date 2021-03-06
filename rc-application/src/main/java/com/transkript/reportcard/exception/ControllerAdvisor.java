package com.transkript.reportcard.exception;

import com.transkript.reportcard.exception.body.ExceptionBody;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @NotNull
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            @NotNull HttpRequestMethodNotSupportedException ex,
            @NotNull HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    // Override methods
    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, @NotNull HttpHeaders headers,
            @NotNull HttpStatus status, @NotNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(
                ExceptionBody.buildExceptionBody(
                        "Validation failed for field(s) in request",
                        request, HttpStatus.BAD_REQUEST, errors),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = {RcAuthenticationException.class})
    protected ResponseEntity<ExceptionBody> handleAuthenticationException(RcAuthenticationException ex, WebRequest wr) {
        logger.error(ex.getMessage());

        return getExceptionEntity(ex, wr, ex.getStatus(), Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<ExceptionBody> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest webRequest
    ) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(
                ExceptionBody.buildExceptionBody(
                        "Data integrity error. Verify with admin/developers to fix this.",
                        webRequest,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        Map.of()
                ), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // ReportCard Exception handlers
    @ExceptionHandler(value = {ReportCardException.IllegalArgumentException.class, ReportCardException.IllegalStateException.class})
    protected ResponseEntity<ExceptionBody> handleIllegalArgument(ReportCardException ex, WebRequest request) {
        return getExceptionEntity(ex, request, ex.getStatus(), Map.of("message", ex.getMessage()));
    }

    // Sub Exception handlers

    @ExceptionHandler(value = {EntityException.NotFound.class})
    protected ResponseEntity<ExceptionBody> handleEntityNotFound(EntityException.NotFound ex, WebRequest request) {
        return getExceptionEntity(ex, request, HttpStatus.NOT_FOUND, Map.of("entity", "Entity not found"));
    }

    @ExceptionHandler(value = {EntityException.AlreadyExists.class})
    protected ResponseEntity<ExceptionBody> handleEntityAlreadyExists(EntityException.AlreadyExists ex, WebRequest request) {
        return getExceptionEntity(ex, request, HttpStatus.CONFLICT, Map.of("entity", "Entity already exists"));
    }

    private ResponseEntity<ExceptionBody> getExceptionEntity(
            RuntimeException ex,
            WebRequest webRequest,
            HttpStatus status,
            Map<String, String> messages) {
        ExceptionBody exceptionBody = ExceptionBody.buildExceptionBody(ex.getMessage(), webRequest, status, messages);
        return new ResponseEntity<>(exceptionBody, exceptionBody.getStatus());
    }
}
