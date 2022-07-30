package com.transkript.reportcard.config.constants;

public class ResponseMessage {
    public static class SUCCESS {
        public static String created(String entity) {
            return String.format("Successfully created %s entity", entity);
        }

        public static String updated(String entity) {
            return String.format("Successfully created %s entity", entity);
        }
    }
}
