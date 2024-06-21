package com.todo_list.todo_list_my_artifact.exceptions;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException(String message){

        super( message);

    }
}
