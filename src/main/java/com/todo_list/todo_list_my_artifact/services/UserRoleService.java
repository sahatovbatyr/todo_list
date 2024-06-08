package com.todo_list.todo_list_my_artifact.services;

import com.todo_list.todo_list_my_artifact.dao.UserRoleDao;
import com.todo_list.todo_list_my_artifact.exceptions.EntityNotFoundByIdException;
import com.todo_list.todo_list_my_artifact.models.RoleType;
import com.todo_list.todo_list_my_artifact.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserRoleService   implements EntityService<UserRole, Long> {



    @Autowired
    UserRoleDao userRoleDao;

    public UserRole getByUserRole( RoleType roleType){
       return userRoleDao.findByRoleType(roleType);
    }

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
        List<UserRole> roles = new ArrayList<>();
        roles = userRoleDao.findAll();
        roles.sort(Comparator.comparingLong(UserRole::getId));
        return roles ;
    }

    @Override
    public void delete(UserRole entity) {

    }

    @Override
    public void update(UserRole entity) {

    }

    @Override
    public Page<UserRole> getAll_sortedPaged(int page, int size, String sortBy, Sort.Direction sortDirection) {
        return null;
    }


}
