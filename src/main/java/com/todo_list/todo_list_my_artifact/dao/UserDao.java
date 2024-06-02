package com.todo_list.todo_list_my_artifact.dao;

import com.todo_list.todo_list_my_artifact.exceptions.UserNotFoundException;
import com.todo_list.todo_list_my_artifact.models.RoleType;
import com.todo_list.todo_list_my_artifact.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u FROM User u JOIN u.roles r WHERE r.id=:roleType")
    List<User> findUsers_byRoles_2(@Param("roleType") RoleType roleType) throws UserNotFoundException;
}
