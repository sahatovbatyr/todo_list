package com.todo_list.todo_list_my_artifact.helpers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.todo_list.todo_list_my_artifact.helpers.validation_groups.OnCreate;
import com.todo_list.todo_list_my_artifact.helpers.validation_groups.OnDelete;
import com.todo_list.todo_list_my_artifact.helpers.validation_groups.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

@Data
public class UserDto {

    @NotNull(message = "Id must be not null.", groups = {OnUpdate.class, OnDelete.class} )
    private Long id;

    @Length(min = 5, max = 250, message = "Username length must be in a range 5 and 250 symbols.")
    @NotNull(message = "Username must be not null.", groups = { OnCreate.class, OnUpdate.class, OnDelete.class})
    private String username;


    @NotNull(message = "Password must be not null.", groups = { OnCreate.class, OnUpdate.class})
    @Length(min = 5, max = 250, message = "Password length must be in a range 5 and 250 symbols.",
            groups = { OnCreate.class, OnUpdate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @NotNull(message = "Password Confirm must be not null.", groups = { OnCreate.class, OnUpdate.class })
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordConfirmation;

    @NotNull( message = "Roles must be not null. roles:[ UROLE_1, UROLE_2 ]", groups = { OnCreate.class, OnUpdate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<UserRoleDto> roles;

}
