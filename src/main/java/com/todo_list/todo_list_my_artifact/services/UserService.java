package com.todo_list.todo_list_my_artifact.services;

import com.todo_list.todo_list_my_artifact.dao.UserDao;
import com.todo_list.todo_list_my_artifact.dao.UserRoleDao;
import com.todo_list.todo_list_my_artifact.exceptions.UserNotFoundException;
import com.todo_list.todo_list_my_artifact.models.RoleType;
import com.todo_list.todo_list_my_artifact.models.User;
import com.todo_list.todo_list_my_artifact.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements EntityService<User, Long>   {

    @Autowired
    UserDao userDao;

//    @Autowired
//    UserRoleDao userRoleDao;

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public User getByUsername(String username) {

        return userDao.findByUsername(username);

    }



    @Override
    public void create(User user) {
//        UserRole userRole_1 = userRoleDao.findByRoleType( RoleType.USER);
//        UserRole userRole_2 = userRoleDao.findByRoleType( RoleType.USER);
//
//        Set<UserRole> roles = new HashSet<>();
//        Collections.addAll(roles, userRole_1, userRole_2);
//
//        user.setRoles( roles );

//        userRoleDao.findByRoleType( RoleType.USER );

//        if (userDao.existsByUsername(user.getUsername()) ) {
//            // Заменить на свои исключения
//            throw new RuntimeException("Пользователь с таким именем уже существует");
//        }

//        if (userDao.existsByEmail(user.getEmail())) {
//            throw new RuntimeException("Пользователь с таким email уже существует");
//        }

         userDao.save(user);

    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id)
                .orElseThrow( ()-> new UserNotFoundException("Not found user with Id:"+id));
    }

    @Override
    public List<User> getAll() {

        List<User> users = userDao.findAll();

//        метод ссылка, reference mothod, uproshen. forma lyamdy,sylayetsa na metod User.getId()
//        users.sort(Comparator.comparingLong(User::getId));

        users.sort( (o1, o2) -> { return Long.compare( o1.getId(), o2.getId() ); } );

        return users;
    }

//    before java 8
    public List<User>  getSorted_oldWay( List<User>  users ){

        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Long.compare( o1.getId(), o2.getId());
            }
        });

        return users;

    }

    @Override
    public void delete(User entity) {

        userDao.delete( entity );

    }

    @Override
    public void update(User entity) {
        userDao.save( entity);
    }

    @Override
    public Page<User> getAll_sortedPaged(int page, int size, String sortBy, Sort.Direction sortDirection) {
        return null;
    }
}
