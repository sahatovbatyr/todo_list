package com.todo_list.todo_list_my_artifact.security;

import com.todo_list.todo_list_my_artifact.dao.UserDao;
import com.todo_list.todo_list_my_artifact.models.User;
import com.todo_list.todo_list_my_artifact.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        return JwtEntityFactory.create( user);
    }
}
