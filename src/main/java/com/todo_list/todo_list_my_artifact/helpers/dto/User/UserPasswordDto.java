package com.todo_list.todo_list_my_artifact.helpers.dto.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.todo_list.todo_list_my_artifact.helpers.validation_groups.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserPasswordDto {

    @NotNull(message = "Id must be not null.", groups = {OnUpdate.class } )
    private Long id;

    @Length(min = 5, max = 250, message = "Username length must be in a range 5 and 250 symbols.")
    @NotNull(message = "Username must be not null.", groups = {  OnUpdate.class })
    private String username;


    @NotNull(message = "Password must be not null.", groups = {  OnUpdate.class})
    @Length(min = 5, max = 250, message = "Password length must be in a range 5 and 250 symbols.",
            groups = { OnUpdate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @NotNull(message = "Password must be not null.", groups = {  OnUpdate.class})
    @Length(min = 5, max = 250, message = "Password length must be in a range 5 and 250 symbols.",
            groups = {  OnUpdate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String newPassword;


    @NotNull(message = "Password Confirm must be not null.", groups = {  OnUpdate.class })
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String newPasswordConfirmation;


}
