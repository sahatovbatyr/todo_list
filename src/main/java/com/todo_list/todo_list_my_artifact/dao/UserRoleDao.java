package com.todo_list.todo_list_my_artifact.dao;

import com.todo_list.todo_list_my_artifact.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDao extends JpaRepository<UserRole, Long> {

}
