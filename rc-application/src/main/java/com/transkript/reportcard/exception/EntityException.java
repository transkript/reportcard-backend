package com.transkript.reportcard.exception;

import java.util.Arrays;

public class EntityException {
    public static class NotFound extends ReportCardException {
        public NotFound(String name) {
            super("Entity of type " + name + " not found");
        }

        public NotFound(String name, Long id) {
            super("Entity of type " + name + " with id " + id + " not found");
        }

        public NotFound(String name, Object... ids) {
            super("Entity of type " + name + " with ids " + Arrays.toString(ids) + " not found");
        }
    }

    public static class AlreadyExists extends ReportCardException {
        public AlreadyExists(String name, Long id) {
            super("Entity of type " + name + " with id " + id + " already exists");
        }

        public AlreadyExists(String name, String id) {
            super("Entity of type " + name + " with identifier " + id + " already exists");
        }

        public AlreadyExists(String name, Object... ids) {
            super("Entity of type " + name + " with ids " + Arrays.toString(ids) + " already exists");
        }
    }
}
