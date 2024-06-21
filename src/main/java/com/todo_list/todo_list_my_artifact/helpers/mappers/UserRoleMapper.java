package com.todo_list.todo_list_my_artifact.helpers.mappers;

import com.todo_list.todo_list_my_artifact.helpers.dto.UserRoleDto;
import com.todo_list.todo_list_my_artifact.models.UserRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {

    UserRoleDto userRole_to_userRoleDto(UserRole userRole);
    UserRole  userRoleDto_to_userRole(UserRoleDto userRoleDto);
}
