package com.todo_list.todo_list_my_artifact.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll(){
        return "ALL";

    }


}
