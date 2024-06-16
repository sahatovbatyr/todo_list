package com.todo_list.todo_list_my_artifact.security;

import com.todo_list.todo_list_my_artifact.models.User;
import com.todo_list.todo_list_my_artifact.models.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtEntityFactory {

    public static JwtEntity create (User user) {

        return new JwtEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );

    }


    private static List<GrantedAuthority> mapToGrantedAuthorities(final List<UserRole> roles ) {
        return roles.stream()
                .map( r-> new SimpleGrantedAuthority(r.getRoletype().name()) )
                .collect(Collectors.toList());
    }


}
