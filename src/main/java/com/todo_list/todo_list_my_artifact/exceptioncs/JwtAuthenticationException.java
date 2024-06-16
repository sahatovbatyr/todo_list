package com.todo_list.todo_list_my_artifact.exceptioncs;

public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException(String message) {
        super(message);
    }
}