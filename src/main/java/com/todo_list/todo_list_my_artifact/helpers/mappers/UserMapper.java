package com.todo_list.todo_list_my_artifact.helpers.mappers;


import com.todo_list.todo_list_my_artifact.helpers.dto.UserDto;
import com.todo_list.todo_list_my_artifact.helpers.dto.UserIdDto;
import com.todo_list.todo_list_my_artifact.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = UserRoleMapper.class )
public interface UserMapper {

    @Mapping( source = "roles", target = "roles")
    UserDto userToUserDto(User user);

    @Mapping( source = "roles", target = "roles")
    User userDtoToUser(UserDto userDto);

    UserIdDto userToUserIdDto( User user);

    User userIdDtoToUser(UserIdDto userIdDto);



}
