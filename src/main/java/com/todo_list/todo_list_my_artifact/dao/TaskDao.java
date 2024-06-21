package com.todo_list.todo_list_my_artifact.dao;

import com.todo_list.todo_list_my_artifact.exceptions.EntityNotFoundException;
import com.todo_list.todo_list_my_artifact.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository
public interface TaskDao extends JpaRepository<Task, Long> {

    Optional<Task> findById( Long id) throws EntityNotFoundException;

}
