package com.transkript.reportcard.exception.advice;

import com.transkript.reportcard.exception.EntityException;
import com.transkript.reportcard.exception.ReportCardException;
import com.transkript.reportcard.exception.body.ExceptionBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    // Override methods
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
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

    // ReportCard Exception handlers
    @ExceptionHandler(value = {ReportCardException.IllegalArgumentException.class, ReportCardException.IllegalStateException.class})
    protected ResponseEntity<ExceptionBody> handleIllegalArgument(ReportCardException ex, WebRequest request) {
        return getExceptionEntity(ex, request, ex.getStatus(), Map.of("message", ex.getMessage()));
    }

    // Sub Exception handlers

    @ExceptionHandler(value = {EntityException.EntityNotFoundException.class})
    protected ResponseEntity<ExceptionBody> handleEntityNotFound(EntityException.EntityNotFoundException ex, WebRequest request) {
        return getExceptionEntity(ex, request, HttpStatus.NOT_FOUND, Map.of("entity", "Entity not found"));
    }

    @ExceptionHandler(value = {EntityException.EntityAlreadyExistsException.class})
    protected ResponseEntity<ExceptionBody> handleEntityAlreadyExists(EntityException.EntityAlreadyExistsException ex, WebRequest request) {
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
