package com.todo_list.todo_list_my_artifact.dao;

import com.todo_list.todo_list_my_artifact.exceptions.UserNotFoundException;
import com.todo_list.todo_list_my_artifact.models.RoleType;
import com.todo_list.todo_list_my_artifact.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserDao_Custom   {

    List<User> findBy_specific( ) throws UserNotFoundException;

}
