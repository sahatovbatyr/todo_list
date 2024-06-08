package com.todo_list.todo_list_my_artifact.services;

import com.todo_list.todo_list_my_artifact.dao.TaskDao;
import com.todo_list.todo_list_my_artifact.exceptions.EntityNotFoundByIdException;
import com.todo_list.todo_list_my_artifact.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService implements EntityService<Task, Long> {

    private TaskDao taskDao;

    @Autowired
    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void create(Task task) {
//        Task newTask = new Task();
//        newTask.setTask_date( new Date());
//        newTask.setAutor();
//        newTask.setDesciption( task.getDesciption());
//        newTask.setTitle(task.getTitle());


    }

    @Override
    public Task findById(Long id) {

        return taskDao.findById(id)
                .orElseThrow( ()-> new EntityNotFoundByIdException("Task Not FOUND by id:"+id) );
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
    public Page<Task> getAll_sortedPaged(int pageNumber, int pageSize, String sortBy, Sort.Direction sortDirection) {

        Page<Task> tasks = null;

        try {

            //default ascending
            Sort sort = Sort.by(sortBy).ascending();

            if (sortDirection.equals(Sort.Direction.DESC)) sort = Sort.by(sortBy).descending();

            Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

            taskDao.findAll().forEach(System.out::println);
            tasks = taskDao.findAll(pageable);

        } catch (Exception e) {
            System.out.println("ERROR+++++++++++++++++++++++++++++++++++++++++");
            System.out.println(e.getMessage());
        }

        return  tasks;
    }

}
