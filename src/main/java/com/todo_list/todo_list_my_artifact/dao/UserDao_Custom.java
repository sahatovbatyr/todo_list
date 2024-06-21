package com.todo_list.todo_list_my_artifact.dao;

import com.todo_list.todo_list_my_artifact.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

//@Repository
public interface UserDao_Custom   {

    List<User> findBy_specific( ) throws UsernameNotFoundException;

}
