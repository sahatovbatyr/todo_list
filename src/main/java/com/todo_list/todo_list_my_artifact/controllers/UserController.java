package com.todo_list.todo_list_my_artifact.controllers;

import com.todo_list.todo_list_my_artifact.exceptions.JwtAuthFailException;
import com.todo_list.todo_list_my_artifact.helpers.dto.User.UserDto;
import com.todo_list.todo_list_my_artifact.helpers.dto.User.UserPasswordDto;
import com.todo_list.todo_list_my_artifact.helpers.mappers.UserMapper;
import com.todo_list.todo_list_my_artifact.helpers.validation_groups.OnCreate;
import com.todo_list.todo_list_my_artifact.helpers.validation_groups.OnDelete;
import com.todo_list.todo_list_my_artifact.helpers.validation_groups.OnUpdate;
import com.todo_list.todo_list_my_artifact.models.User;
import com.todo_list.todo_list_my_artifact.services.UserService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll() {
        return "ALL";

    }

    @GetMapping("/get-by-id/{userId}")
    public UserDto findById(@PathVariable final Long userId) {

        log.info("UserController.findById() userId:" + userId);
        User user = userService.findById(userId);

        return userMapper.userToUserDto(user);
    }

    @PostMapping("/create")
    public UserDto create(@Validated(OnCreate.class) @RequestBody final   UserDto userDto) {

        log.info("UserController.create() new user:" + userDto);
        User user = userService.createByDto(userDto);

        return userMapper.userToUserDto(user);

    }

    @PostMapping("/update-my-password")
    public void update_my_own_password(@RequestBody @Validated(OnUpdate.class) final UserPasswordDto userDto) {

        log.info("UserController.update_my_own_password() user:{}" , userDto);
        userService.update_my_password(userDto);

    }

    @PostMapping("/update-roles")
    @Secured({"ADMIN"})
    public void update_roles(@RequestBody @Validated(OnUpdate.class) final UserDto userDto) {

        log.info("UserController.update() user:" + userDto);

        if (!userDto.getPassword().equals(userDto.getPasswordConfirmation())) {
            throw new JwtAuthFailException("Password and confirmation not equal.");
        }

        userService.update_roles(userDto);

    }

    @PostMapping("/update-is-active")
    @Secured({"ADMIN"})
    public void update_is_active(
            @RequestParam final boolean isActive,
            @RequestBody @Validated(OnUpdate.class) UserDto userDto) {

        log.info("UserController.update_is_active() userId:{}" , userDto.getId());
        userService.update_activity(userDto.getId(), isActive);

    }


    @PostMapping("/delete")
    @Secured({"ADMIN"})
    public void delete( @RequestBody @Validated(OnDelete.class) UserDto userDto) {

        log.info("UserController.delete() userId:" + userDto.getId());
        userService.deleteById( userDto.getId());

    }

    @GetMapping("/get-all-sorted")
//    @Secured({"ADMIN"})
    public Page<UserDto>  getAllSorted( @RequestParam(defaultValue = "0")  @Min(0) int pageNumber,
                              @RequestParam(defaultValue = "30") @Min(1) @Max(100) int pageSize,
                              @RequestParam(defaultValue = "id") String sortBy,
                              @RequestParam(defaultValue = "ASC")  Sort.Direction direction) {

        log.info("UserController.getAllSorted() " );
        Page<UserDto> userPage = userService.getAllUsers_sortedPaged( pageNumber, pageSize, sortBy, direction );
        return userPage;

    }






}
