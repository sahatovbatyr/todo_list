package com.todo_list.todo_list_my_artifact.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_and_roles",
            joinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false, updatable = false))
    private Set<UserRole> roles ;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}
