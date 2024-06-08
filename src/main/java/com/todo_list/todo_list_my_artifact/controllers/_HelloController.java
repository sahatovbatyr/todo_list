package com.todo_list.todo_list_my_artifact.controllers;

import com.todo_list.todo_list_my_artifact.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test_conn")
@RequiredArgsConstructor
//@Tag(name = "Примеры", description = "Примеры запросов с разными правами доступа")
public class _HelloController {

    private final UserService userService;

    @GetMapping("/hello")
//    @Operation (summary = "Доступен только авторизованным пользователям")
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/admin")
//    @Operation(summary = "Доступен только авторизованным пользователям с ролью ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public String exampleAdmin() {
        return "Hello, admin!";
    }

    @GetMapping("/get-admin")
//    @Operation(summary = "Получить роль ADMIN (для демонстрации)")
    public void getAdmin() {
//        service.getAdmin();
    }

}
