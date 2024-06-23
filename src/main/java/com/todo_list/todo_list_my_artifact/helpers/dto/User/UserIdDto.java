package com.todo_list.todo_list_my_artifact.helpers.dto.User;

import com.todo_list.todo_list_my_artifact.helpers.validation_groups.OnCreate;
import com.todo_list.todo_list_my_artifact.helpers.validation_groups.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserIdDto {

    @NotNull(message = "User id must be not null.", groups = { OnCreate.class, OnUpdate.class } )
    private Long id;
}
