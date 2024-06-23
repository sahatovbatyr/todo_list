package com.todo_list.todo_list_my_artifact.services;

import com.todo_list.todo_list_my_artifact.controllers.UserController;
import com.todo_list.todo_list_my_artifact.dao.UserDao;
import com.todo_list.todo_list_my_artifact.exceptions.AppErrorException;
import com.todo_list.todo_list_my_artifact.exceptions.EntityNotFoundException;
import com.todo_list.todo_list_my_artifact.exceptions.InvalidValueException;
import com.todo_list.todo_list_my_artifact.exceptions.JwtAuthFailException;
import com.todo_list.todo_list_my_artifact.helpers.dto.User.UserDto;
import com.todo_list.todo_list_my_artifact.helpers.dto.User.UserPasswordDto;
import com.todo_list.todo_list_my_artifact.helpers.mappers.UserMapper;
import com.todo_list.todo_list_my_artifact.models.RoleType;
import com.todo_list.todo_list_my_artifact.models.User;
import com.todo_list.todo_list_my_artifact.models.UserRole;
import com.todo_list.todo_list_my_artifact.security.JwtEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements EntityService<User, UserDto, Long> {

    private final UserDao userDao;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


////    @Autowired
////    UserRoleDao userRoleDao;
//
//    /**
//     * Получение пользователя по имени пользователя
//     *
//     * @return пользователь
//     */
//    public User getByUsername(String username) {
//
//        return userDao.findByUsername(username)
//                .orElseThrow(()-> new UserNotFoundException("User not found:"+ username));
//
//    }


    @Override
    public User create(final User user) {

        if (userDao.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("User already exist:" + user.getUsername());
        }

        if (user.getRoles().isEmpty()) {
            UserRole userRole = userRoleService.getByUserRole(RoleType.USER);
            user.setRoles(Set.of(userRole));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newRec = userDao.save(user);
        return newRec;

    }

    @Override
    public User createByDto(UserDto userDto) {

        if (!userDto.getPassword().equals(userDto.getPasswordConfirmation())) {
            throw new IllegalStateException("The passwords not match.");
        }

        if (userDao.findByUsername(userDto.getUsername()).isPresent()) {
            throw new IllegalStateException("User already exist:" + userDto.getUsername());
        }

        User user = userMapper.userDtoToUser(userDto);

        log.info("UserService.createByDto()");
        log.info("userDto.getRoles()="+userDto.getRoles());

        if (userDto.getRoles().isEmpty()) {
            UserRole userRole = userRoleService.getByUserRole(RoleType.USER);
            user.setRoles(Set.of(userRole));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userDao.save(user);
        return user;
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found user with Id:" + id));
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Authentication Fail. Username or password not match!"));
    }

    @Override
    public List<User> getAll() {

        List<User> users = userDao.findAll();

//        метод ссылка, reference mothod, uproshen. forma lyamdy,sylayetsa na metod User.getId()
//        users.sort(Comparator.comparingLong(User::getId));

        users.sort((o1, o2) -> {
            return Long.compare(o1.getId(), o2.getId());
        });

        return users;
    }

    //    before java 8
    public List<User> getSorted_oldWay(List<User> users) {

        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Long.compare(o1.getId(), o2.getId());
            }
        });

        return users;

    }

    @Override
    public void delete(User entity) {

        userDao.delete(entity);

    }

    @Override
    @Transactional
    public void deleteById(Long userId) {

//        User user = findById(userId);
//        userDao.delete(user);
        userDao.deleteById(userId);

    }

    @Override
    public void update(User entity) {
//        userDao.save( entity);
    }

    @Override
    public Page<User> getAll_sortedPaged(int pageNumber, int pageSize, String sortBy, Sort.Direction sortDirection) {

        Page<User> userPage = null;

        return userPage;

    }

    public Page<UserDto> getAllUsers_sortedPaged(int pageNumber, int pageSize, String sortBy, Sort.Direction sortDirection) {

        Page<User> userPage = null;

        try {
            Sort sort = Sort.by(sortBy).ascending();
            if ( sortDirection.equals( Sort.Direction.DESC)) { Sort.by(sortBy).ascending(); }
            Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
            userPage = userDao.findAll(pageable);

            List<UserDto> userDtoList = userPage.getContent().stream()
                    .map( u-> userMapper.userToUserDto(u))
                    .toList();

            return new PageImpl<>( userDtoList, pageable,userPage.getTotalElements());

        } catch ( Exception ex ) {
            log.error("UserController.getAllUsers_sortedPaged() error:{}", ex.getMessage(), ex);
            throw new AppErrorException("Server Error. message:"+ex.getMessage());
        }


    }

    public User update_roles(final UserDto userDto) {
        User userFromDto = userMapper.userDtoToUser(userDto);
        User user = findById(userDto.getId());
        user.setRoles(userFromDto.getRoles());
        userDao.save(user);

        return user;

    }

    @Transactional
    public void update_my_password(final UserPasswordDto userPasswordDto) {

        if (!userPasswordDto.getNewPassword().equals(userPasswordDto.getNewPasswordConfirmation())) {
            throw new InvalidValueException("New Password and confirmation not equal.");
        }

        String authUser = getCurrentAuthUser();

        if (!authUser.equals(userPasswordDto.getUsername())) {
            throw new AccessDeniedException("Access denied. You can only change your own password.");
        }

        User user = findByUsername(userPasswordDto.getUsername());

        if ( !passwordEncoder.matches( userPasswordDto.getPassword(),  user.getPassword() ) ) {
            log.warn( "Auth ERROR UserService.update_my_password() :{}", userPasswordDto.getUsername() );
            throw new JwtAuthFailException("Authenticate error. Username or password not match.");
        } else {
            user.setPassword(passwordEncoder.encode(userPasswordDto.getNewPassword()));
            userDao.save(user);
        }

    }

    public User update_activity(final Long userId, final boolean isActive) {
        User user = findById(userId);
        user.setActive(isActive);
        userDao.save(user);
        return user;

    }

    public String getCurrentAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new JwtAuthFailException("Authentication Fail. Sign in please!");
        }

        JwtEntity jwtEntity = (JwtEntity) authentication.getPrincipal();
        return jwtEntity.getUsername();

    }

    public Long getCurrentAuthUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new JwtAuthFailException("Authentication Fail. Sign in please!");
        }

        JwtEntity jwtEntity = (JwtEntity) authentication.getPrincipal();
        return jwtEntity.getId();

    }




}
