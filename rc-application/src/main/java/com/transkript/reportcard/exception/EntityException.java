package com.transkript.reportcard.exception;

import java.util.Arrays;

public class EntityException {
    public static class EntityNotFoundException extends ReportCardException {
        public EntityNotFoundException(String name) {
            super("Entity of type " + name + " not found");
        }

        public EntityNotFoundException(String name, Long id) {
            super("Entity of type " + name + " with id " + id + " not found");
        }

        public EntityNotFoundException(String name, Object... ids) {
            super("Entity of type " + name + " with ids " + Arrays.toString(ids) + " not found");
        }
    }

    public static class EntityAlreadyExistsException extends ReportCardException {
        public EntityAlreadyExistsException(String name, Long id) {
            super("Entity of type " + name + " with id " + id + " already exists");
        }

        public EntityAlreadyExistsException(String name, String id) {
            super("Entity of type " + name + " with identifier " + id + " already exists");
        }

        public EntityAlreadyExistsException(String name, Object... ids) {
            super("Entity of type " + name + " with ids " + Arrays.toString(ids) + " already exists");
        }
    }
}
