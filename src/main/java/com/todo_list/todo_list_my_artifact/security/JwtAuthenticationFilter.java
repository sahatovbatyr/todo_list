package com.todo_list.todo_list_my_artifact.security;

import com.todo_list.todo_list_my_artifact.exceptions.JwtAuthFailException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    Logger LOGGER = LogManager.getLogger(JwtAuthenticationFilter.class);

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final JwtTokenProvider jwtTokenProvider;

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            LOGGER.info("JwtAuthenticationFilter.doFilterInternal()" + request.getRequestURL());

            // Получаем токен из заголовка
            String authHeader = request.getHeader(HEADER_NAME);
            if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }

            // Обрезаем префикс и получаем имя пользователя из токена
            String jwt = authHeader.substring(BEARER_PREFIX.length());
            String username = jwtTokenProvider.getUsername(jwt);

            LOGGER.info("JwtAuthenticationFilter.doFilterInternal() username:" + username);

            // Если токен валиден, то аутентифицируем пользователя
            if (jwtTokenProvider.isValid(jwt) && SecurityContextHolder.getContext().getAuthentication() == null ) {

                Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
            filterChain.doFilter(request, response);

        }  catch (AuthenticationException e) {
            throw e;
        } catch (Exception ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }


    }

}
