package com.todo_list.todo_list_my_artifact.dao;

import com.todo_list.todo_list_my_artifact.exceptions.EntityNotFoundException;
import com.todo_list.todo_list_my_artifact.models.RoleType;
import com.todo_list.todo_list_my_artifact.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Long> {

   Optional<UserRole>  findByRoletype(RoleType roleType) throws EntityNotFoundException;

}
