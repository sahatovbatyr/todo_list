package com.todo_list.todo_list_my_artifact.config;

import com.todo_list.todo_list_my_artifact.services.JwtService;
import com.todo_list.todo_list_my_artifact.services.MyUserDetailsService;
import com.todo_list.todo_list_my_artifact.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;


import org.springframework.web.filter.OncePerRequestFilter;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter  extends OncePerRequestFilter {

    Logger LOGGER = LogManager.getLogger(JwtAuthenticationFilter.class);

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final JwtService jwtService;
//    private final UserService userService;
    private final MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal( @NonNull HttpServletRequest request,
                                     @NonNull HttpServletResponse response,
                                     @NonNull FilterChain filterChain ) throws ServletException, IOException {

        LOGGER.info("JwtAuthenticationFilter.doFilterInternal()"+request.getRequestURL());



        // Получаем токен из заголовка
        var authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Обрезаем префикс и получаем имя пользователя из токена
        var jwt = authHeader.substring(BEARER_PREFIX.length());

//        try {
            var username = jwtService.extractUserName(jwt);

            if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Если токен валиден, то аутентифицируем пользователя
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    SecurityContext context = SecurityContextHolder.createEmptyContext();

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    context.setAuthentication(authToken);
                    SecurityContextHolder.setContext(context);
                }
            }

//        } catch ( ExpiredJwtException ex) {
//            LOGGER.error( "Token period expired!!!" );
//            response.setStatus(401);
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token period expired! JWT аутентификация не удалась");
//
//        } catch ( SignatureException ex) {
//            LOGGER.error( "Signing ERROR");
//            response.setStatus(401);
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT аутентификация не удалась.Signing ERROR");
//
//        } catch ( Exception ex) {
//            response.setStatus(401);
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT аутентификация не удалась"+ex.getMessage());
//
//
//        }



        filterChain.doFilter(request, response);

    }
}
