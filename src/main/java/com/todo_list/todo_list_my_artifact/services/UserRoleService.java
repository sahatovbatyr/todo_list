package com.todo_list.todo_list_my_artifact.services;

import com.todo_list.todo_list_my_artifact.dao.UserRoleDao;
import com.todo_list.todo_list_my_artifact.exceptions.EntityNotFoundByIdException;
import com.todo_list.todo_list_my_artifact.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService implements EntityService<UserRole, Long> {

    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public void create(UserRole entity) {
        userRoleDao.save(entity);

    }

    @Override
    public UserRole findById(Long id) {

        return userRoleDao.findById(id)
                .orElseThrow( ()->new EntityNotFoundByIdException("UserRole not found with id:" +id));
    }

    @Override
    public List<UserRole> getAll() {
        return  userRoleDao.findAll();
    }

    @Override
    public void delete(UserRole entity) {

    }

    @Override
    public void update(UserRole entity) {

    }
}
