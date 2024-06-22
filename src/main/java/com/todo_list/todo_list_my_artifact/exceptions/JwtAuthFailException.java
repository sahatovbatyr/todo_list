package com.todo_list.todo_list_my_artifact.exceptions;

public class JwtAuthFailException extends RuntimeException {
    public JwtAuthFailException(String message) {
        super(message);
    }

    public JwtAuthFailException(String message, Throwable cause) {
        super(message, cause);
    }
}