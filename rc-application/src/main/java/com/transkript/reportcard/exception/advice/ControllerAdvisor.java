package com.transkript.reportcard.exception.advice;

import com.transkript.reportcard.exception.EntityException;
import com.transkript.reportcard.exception.body.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {EntityException.EntityNotFoundException.class})
    protected ResponseEntity<ExceptionBody> handleEntityNotFound(EntityException.EntityNotFoundException ex, WebRequest request) {
        return getExceptionEntity(ex, request, HttpStatus.NOT_FOUND, List.of("Entity not found"));
    }

    @ExceptionHandler(value = {EntityException.EntityAlreadyExistsException.class})
    protected ResponseEntity<ExceptionBody> handleEntityAlreadyExists(EntityException.EntityAlreadyExistsException ex, WebRequest request) {
        return getExceptionEntity(ex, request, HttpStatus.CONFLICT, List.of("Entity already exists"));
    }

    private ResponseEntity<ExceptionBody> getExceptionEntity(
            RuntimeException ex,
            WebRequest webRequest,
            HttpStatus status,
            List<String> messages) {
        ExceptionBody exceptionBody = ExceptionBody.buildExceptionBody(ex, webRequest, status, messages);
        return new ResponseEntity<>(exceptionBody, exceptionBody.getStatus());
    }
}
