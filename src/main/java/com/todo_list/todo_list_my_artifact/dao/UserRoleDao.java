package com.todo_list.todo_list_my_artifact.dao;

import com.todo_list.todo_list_my_artifact.models.RoleType;
import com.todo_list.todo_list_my_artifact.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Long> {

    UserRole  findByRoleType(RoleType roleType);

}
