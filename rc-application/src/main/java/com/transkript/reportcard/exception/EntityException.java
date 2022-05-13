package com.transkript.reportcard.exception;

public class EntityException {
    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String name ){
            super("Entity of type " + name + " not found");
        }
    }

    public static class EntityAlreadyExistsException extends RuntimeException {
        public EntityAlreadyExistsException(String name, Long id){
            super("Entity of type " + name + " with id " + id + " already exists");
        }
    }
}
