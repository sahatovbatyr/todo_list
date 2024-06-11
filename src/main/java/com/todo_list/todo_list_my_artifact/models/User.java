package com.todo_list.todo_list_my_artifact.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users" )
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

//    private boolean isActive;


    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_and_roles",
            joinColumns = @JoinColumn(name = "user_id", nullable = false ),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false ))
    @JsonIgnore
    private Set<UserRole> roles ;

//    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Task> createdTasks = new ArrayList<>() ;

//    @OneToMany(mappedBy = "assignedTo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Task> assignedTasks = new ArrayList<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities;

        authorities = getRoles().stream()
                .map( r-> new SimpleGrantedAuthority( r.getRoletype().name() ) )
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\": \"").append(getId()).append("\", ");
        sb.append("\"name\": \"").append(this.username).append("\", ");
        sb.append("\"password\": \"[PROTECTED]\"").append("}");
        return sb.toString();
    }

}
