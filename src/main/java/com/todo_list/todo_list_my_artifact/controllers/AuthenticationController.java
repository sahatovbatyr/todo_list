package com.todo_list.todo_list_my_artifact.controllers;

import com.todo_list.todo_list_my_artifact.authDTO.JwtAuthResponseDto;
import com.todo_list.todo_list_my_artifact.authDTO.JwtRequestDto;
import com.todo_list.todo_list_my_artifact.services.AuthenticationService;
import com.todo_list.todo_list_my_artifact.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthenticationController {

    Logger LOGGER = LogManager.getLogger( AuthenticationController.class);


    private final AuthenticationService authenticationService;

    @PostMapping("/signin")
    public JwtAuthResponseDto createAuthToken(@RequestBody JwtRequestDto requestDto){

        LOGGER.info(requestDto.toString());

        var token = authenticationService.signIn( requestDto );

       return token;
    }

    @PostMapping("/test")
    public String testing(@RequestBody String requestDto){

        return requestDto;
    }
}
