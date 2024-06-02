package com.todo_list.todo_list_my_artifact.dao;

import com.todo_list.todo_list_my_artifact.exceptions.UserNotFoundException;
import com.todo_list.todo_list_my_artifact.models.RoleType;
import com.todo_list.todo_list_my_artifact.models.User;

import java.util.List;

public interface UserDao_Custom {

    List<User> findUsers_byRoles(RoleType roleType) throws UserNotFoundException;

}
