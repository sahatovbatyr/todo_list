package com.todo_list.todo_list_my_artifact.services;

import com.todo_list.todo_list_my_artifact.authDTO.JwtAuthenticationResponse;
import com.todo_list.todo_list_my_artifact.authDTO.SignInRequest;
import com.todo_list.todo_list_my_artifact.authDTO.SignUpRequest;
import com.todo_list.todo_list_my_artifact.models.RoleType;
import com.todo_list.todo_list_my_artifact.models.User;
import com.todo_list.todo_list_my_artifact.models.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


//@Service
//@RequiredArgsConstructor
public class AuthenticationService {
//
//    private final UserService userService;
//    private final JwtService jwtService;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//
//    private UserRoleService userRoleService;
//
//
//    /**
//     * Регистрация пользователя
//     *
//     * @param request данные пользователя
//     * @return токен
//     */
//    public JwtAuthenticationResponse signUp(SignUpRequest request) {
//
//        Set<UserRole> roles = new HashSet<>();
//
//        UserRole userRole_1 = userRoleService.getByUserRole( RoleType.USER );
//        UserRole userRole_2 = userRoleService.getByUserRole( RoleType.GUEST );
//        roles.add( userRole_1);
//        roles.add( userRole_2);
//
//        var user = User.builder()
//                .username(request.getUsername())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .roles( roles )
//                .build();
//
//        userService.create(user);
//
//        var jwt = jwtService.generateToken(user);
//        return new JwtAuthenticationResponse(jwt);
//    }
//
//    /**
//     * Аутентификация пользователя
//     *
//     * @param request данные пользователя
//     * @return токен
//     */
//    public JwtAuthenticationResponse signIn(SignInRequest request) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                request.getUsername(),
//                request.getPassword()
//        ));
//
//        var user = userService
//                .userDetailsService()
//                .loadUserByUsername(request.getUsername());
//
//        var jwt = jwtService.generateToken(user);
//        return new JwtAuthenticationResponse(jwt);
//    }
//
//


}
