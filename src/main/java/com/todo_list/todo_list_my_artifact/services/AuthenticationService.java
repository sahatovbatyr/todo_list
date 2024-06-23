package com.todo_list.todo_list_my_artifact.services;

import com.todo_list.todo_list_my_artifact.authDTO.JwtRequestDto;
import com.todo_list.todo_list_my_artifact.authDTO.JwtResponseDto;
import com.todo_list.todo_list_my_artifact.authDTO.SignUpRequestDto;
import com.todo_list.todo_list_my_artifact.exceptions.JwtAuthFailException;
import com.todo_list.todo_list_my_artifact.models.User;
import com.todo_list.todo_list_my_artifact.security.JwtEntity;
import com.todo_list.todo_list_my_artifact.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    Logger LOGGER = LogManager.getLogger( AuthenticationService.class );

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenService;

//    private final UserDetailsService myUserDetailsService;

//    private final PasswordEncoder passwordEncoder;


    private UserRoleService userRoleService;


    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtResponseDto signUp(SignUpRequestDto request) {

        JwtResponseDto responseDto = new JwtResponseDto();

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
//        var jwt = jwtTokenService.generateToken(user);
        return  responseDto;
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtResponseDto signIn( final JwtRequestDto request) {

        JwtResponseDto jwtResponse = new JwtResponseDto();

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));

            User user = userService.findByUsername(request.getUsername());

            String accessToken= jwtTokenService.createAccessToken(
                    user.getId(), user.getUsername(), user.getRoles()
            );

            String refreshToken = jwtTokenService.createRefreshToken(
                    user.getId(), user.getUsername()
            );
            jwtResponse.setId(user.getId());
            jwtResponse.setUsername(user.getUsername());
            jwtResponse.setAccessToken( accessToken );
            jwtResponse.setRefreshToken( refreshToken );

            return  jwtResponse;


        } catch ( BadCredentialsException ex) {
            throw  new JwtAuthFailException("Authentication failed.Username or password not found. "
                    +ex.getMessage());
        }
        catch ( AuthenticationException ex) {
           throw  new JwtAuthFailException("Authentication failed." +ex.getMessage());

        } catch ( Exception ex) {
            throw  new JwtAuthFailException("Authentication failed." + ex.getMessage());
        }

    }


    public JwtResponseDto refresh( final String refreshToken    ) {
        return jwtTokenService.refreshUserTokens(refreshToken);
    }






}
