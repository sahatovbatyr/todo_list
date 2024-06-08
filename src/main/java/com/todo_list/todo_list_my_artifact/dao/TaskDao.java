package com.todo_list.todo_list_my_artifact.dao;

import com.todo_list.todo_list_my_artifact.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends JpaRepository<Task, Long> {

}
