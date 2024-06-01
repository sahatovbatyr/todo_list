package com.todo_list.todo_list_my_artifact.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotNull
    @Column(name = "role_type")
    private RoleType roleType;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();



}
