package com.todo_list.todo_list_my_artifact.exceptions;

public class AppErrorException extends RuntimeException {
    public AppErrorException(String message) {
        super(message);
    }
}
