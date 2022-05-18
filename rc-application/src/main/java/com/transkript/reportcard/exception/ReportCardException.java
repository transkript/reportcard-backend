package com.transkript.reportcard.exception;

public class ReportCardException {
    public static class IllegalArgumentException extends RuntimeException {
        public IllegalArgumentException(String message) {
            super(message);
        }
    }
}
