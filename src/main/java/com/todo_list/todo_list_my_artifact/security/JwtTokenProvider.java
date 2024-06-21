package com.todo_list.todo_list_my_artifact.security;

import com.todo_list.todo_list_my_artifact.authDTO.JwtResponseDto;
import com.todo_list.todo_list_my_artifact.exceptions.JwtAuthenticationException;
import com.todo_list.todo_list_my_artifact.models.User;
import com.todo_list.todo_list_my_artifact.models.UserRole;
import com.todo_list.todo_list_my_artifact.props.JwtProperties;
import com.todo_list.todo_list_my_artifact.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    Logger LOGGER = LogManager.getLogger(JwtTokenProvider.class);

    private final JwtProperties jwtProperties;

    private final MyUserDetailsService myUserDetailsService;
    private final UserService userService;
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String createAccessToken(
            final Long userId,
            final String username,
            final Set<UserRole> roles
    ) {
        Claims claims = Jwts.claims()
                .subject(username)
                .add("id", userId)
                .add("roles", resolveRoles(roles))
                .build();
//        Instant validity = Instant.now()
//                .plus(jwtProperties.getAccessTime(), ChronoUnit.HOURS);
        Date expirationDate = new Date(
                System.currentTimeMillis() + jwtProperties.getAccessPeriod().toMillis()
        );
        return Jwts.builder()
                .claims(claims)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }

    private List<String> resolveRoles(  final Set<UserRole> roles  ) {
        return roles.stream()
                .map( ur-> ur.getRoletype().name() )
                .collect(Collectors.toList());
    }

    public String createRefreshToken(
            final Long userId,
            final String username
    ) {
        Claims claims = Jwts.claims()
                .subject(username)
                .add("id", userId)
                .build();
        Date expirationDate = new Date( System.currentTimeMillis()
                + jwtProperties.getAccessPeriod().toMillis()  );
        return Jwts.builder()
                .claims(claims)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public JwtResponseDto refreshUserTokens(
            final String refreshToken
    ) {
        JwtResponseDto jwtResponse = new JwtResponseDto();
        if (!isValid(refreshToken)) {
            LOGGER.info("Not valid Token:");
            throw new JwtAuthenticationException("Access denied.");
        }
        Long userId = Long.valueOf(getId(refreshToken));
        User user = userService.findById(userId);
        jwtResponse.setId(userId);
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(
                createAccessToken(userId, user.getUsername(), user.getRoles())
        );
        jwtResponse.setRefreshToken(
                createRefreshToken(userId, user.getUsername())
        );
        return jwtResponse;
    }

    public boolean isValid( final String token  ) {
        Jws<Claims> claims = Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
        return claims.getPayload()
                .getExpiration()
                .after(new Date());
    }

    private String getId( final String token ) {
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id", String.class);
    }

    public String getUsername( final String token ) {
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Authentication getAuthentication(final String token ) {
        String username = getUsername(token);
        UserDetails userDetails = myUserDetailsService.loadUserByUsername( username );
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                "",
                userDetails.getAuthorities()
        );
    }




}
