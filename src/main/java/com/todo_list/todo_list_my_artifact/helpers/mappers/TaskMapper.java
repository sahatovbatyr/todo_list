package com.todo_list.todo_list_my_artifact.helpers.mappers;


import com.todo_list.todo_list_my_artifact.helpers.dto.TaskDto;
import com.todo_list.todo_list_my_artifact.models.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto taskToTaskDto(Task task);
    Task taskDtoToTask( TaskDto taskDto);

}
