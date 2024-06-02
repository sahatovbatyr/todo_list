package com.todo_list.todo_list_my_artifact.exceptions;

public class EntityNotFoundByIdException extends RuntimeException{

    public EntityNotFoundByIdException( String message) {
        super(message);
    }
}
