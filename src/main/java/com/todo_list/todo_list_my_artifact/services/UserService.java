package com.todo_list.todo_list_my_artifact.services;

import com.todo_list.todo_list_my_artifact.dao.UserDao;
import com.todo_list.todo_list_my_artifact.exceptions.UserNotFoundException;
import com.todo_list.todo_list_my_artifact.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements EntityService<User, Long> {

    @Autowired
    UserDao userDao;


    @Override
    public void create(User entity) {

        userDao.save(entity);

    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id)
                .orElseThrow( ()-> new UserNotFoundException("Not found user with Id:"+id));
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public void delete(User entity) {

        userDao.delete( entity );

    }

    @Override
    public void update(User entity) {
        userDao.save( entity);
    }
}
