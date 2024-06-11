package com.todo_list.todo_list_my_artifact.services;

import com.todo_list.todo_list_my_artifact.authDTO.JwtAuthResponseDto;
import com.todo_list.todo_list_my_artifact.authDTO.JwtRequestDto;
import com.todo_list.todo_list_my_artifact.authDTO.SignUpRequestDto;
import com.todo_list.todo_list_my_artifact.models.RoleType;
import com.todo_list.todo_list_my_artifact.models.User;
import com.todo_list.todo_list_my_artifact.models.UserRole;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    Logger LOGGER = LogManager.getLogger( AuthenticationService.class );

    private final UserService userService;
    private final MyUserDetailsService myUserDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private UserRoleService userRoleService;


    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthResponseDto signUp(SignUpRequestDto request) {

        Set<UserRole> roles = new HashSet<>();

        UserRole userRole_1 = userRoleService.getByUserRole( RoleType.USER );
        UserRole userRole_2 = userRoleService.getByUserRole( RoleType.GUEST );
        roles.add( userRole_1);
        roles.add( userRole_2);

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles( roles )
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthResponseDto(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthResponseDto signIn(JwtRequestDto request) {

        System.out.println( "*********************signIn(JwtRequestDto request)"+request.toString());
        LOGGER.info("***********************signIn() ");
        LOGGER.info("***********************signIn() "+ request.toString());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = myUserDetailsService.loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthResponseDto(jwt);
    }




}
