package com.todo_list.todo_list_my_artifact.services;

import com.todo_list.todo_list_my_artifact.dao.TaskDao;
import com.todo_list.todo_list_my_artifact.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements EntityService<Task, Long> {

    @Autowired
    TaskDao taskDao;

    @Override
    public void create(Task entity) {

    }

    @Override
    public Task findById(Long aLong) {
        return null;
    }

    @Override
    public List<Task> getAll() {
        return List.of();
    }

    @Override
    public void delete(Task entity) {

    }

    @Override
    public void update(Task entity) {

    }

    @Override
    public Page<Task>  getAll_sortedPaged(int pageNumber, int pageSize, String sortBy, Sort.Direction sortDirection) {

        //default ascending
        Sort sort  = Sort.by(sortBy).ascending();

        if ( sortDirection.equals( Sort.Direction.DESC )) sort = Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort );

        return taskDao.findAll(pageable);
    }
}
