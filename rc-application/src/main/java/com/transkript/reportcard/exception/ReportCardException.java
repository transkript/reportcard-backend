package com.transkript.reportcard.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ReportCardException extends RuntimeException {
    HttpStatus status;

    public ReportCardException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ReportCardException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public static class IllegalArgumentException extends ReportCardException {
        public IllegalArgumentException(String message) {
            super(message);
        }

        public IllegalArgumentException(String message, HttpStatus status) {
            super(message, status);
        }
    }

    public static class IllegalStateException extends ReportCardException {
        public IllegalStateException(String message) {
            super(message);
        }

        public IllegalStateException(String message, HttpStatus status) {
            super(message, status);
        }
    }
}
