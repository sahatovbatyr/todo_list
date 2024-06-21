package com.todo_list.todo_list_my_artifact.dao;

import com.todo_list.todo_list_my_artifact.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

//@Repository
public interface UserDao extends JpaRepository<User, Long> {

//    @Query("SELECT DISTINCT u FROM users u JOIN u.roles r WHERE r.id=:roleType")
//    List<User> findUsers_byRoles_2(@Param("roleType") RoleType roleType) throws UserNotFoundException;

    Optional<User> findByUsername(String username) throws UsernameNotFoundException;

//    boolean existsByUsername(String username);
//    boolean existsByEmail(String email);
}
