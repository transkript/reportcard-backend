package com.transkript.reportcard.exception;

public class EntityException {
    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String name){
            super("Entity of type " + name + " not found");
        }
        public EntityNotFoundException(String name, Long id) {
            super("Entity of type " + name + " with id " + id + " not found");
        }
    }

    public static class EntityAlreadyExistsException extends RuntimeException {
        public EntityAlreadyExistsException(String name, Long id){
            super("Entity of type " + name + " with id " + id + " already exists");
        }
        public EntityAlreadyExistsException(String name, String id) {
            super("Entity of type " + name + " with identifier " + id + " already exists");
        }
    }
}
