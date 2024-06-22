package com.todo_list.todo_list_my_artifact.helpers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todo_list.todo_list_my_artifact.helpers.validation_groups.OnCreate;
import com.todo_list.todo_list_my_artifact.helpers.validation_groups.OnDelete;
import com.todo_list.todo_list_my_artifact.helpers.validation_groups.OnUpdate;
import com.todo_list.todo_list_my_artifact.models.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TaskDto {

    @NotNull(message = "Id must be not null.", groups = { OnUpdate.class, OnDelete.class} )
    private Long id;

    @NotNull(message = "Title must be not null.", groups = { OnCreate.class, OnUpdate.class})
    @Length( max = 250, message = "Title length must be less than 250 chars.")
    private String title;

    @NotNull(message = "Desciption must be not null.", groups = { OnCreate.class, OnUpdate.class})
    @Length( max = 250, message = "Desciption length must be less than 250 chars.")
    private String desciption;

//    @NotNull(message = "Desciption must be not null.", groups = { OnCreate.class, OnUpdate.class})
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date taskDate;

    @NotNull(message = "Specify assigned User", groups = { OnCreate.class, OnUpdate.class})
    private UserIdDto assignedTo;

    @NotNull(message = "Specify assigned User", groups = {  OnUpdate.class})
    private boolean completed;
}
