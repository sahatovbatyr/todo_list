package com.todo_list.todo_list_my_artifact.controllers;

import com.todo_list.todo_list_my_artifact.authDTO.JwtRequestDto;
import com.todo_list.todo_list_my_artifact.authDTO.JwtResponseDto;
import com.todo_list.todo_list_my_artifact.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthenticationController {

    Logger LOGGER = LogManager.getLogger( AuthenticationController.class);
    private final AuthenticationService authenticationService;

    @PostMapping("/signin")
    public JwtResponseDto createAuthToken(@Validated @RequestBody JwtRequestDto requestDto){

        LOGGER.info("Request to authinticate for "+requestDto.toString());
        var token = authenticationService.signIn( requestDto );

       return token;
    }

    @PostMapping("/test")
    public String testing(@RequestBody String requestDto){

        return requestDto;
    }
}
