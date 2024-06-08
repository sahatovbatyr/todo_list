package com.todo_list.todo_list_my_artifact.controllers;

import com.todo_list.todo_list_my_artifact.models.Task;
import com.todo_list.todo_list_my_artifact.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("get-all-sorted")
    public Page<Task> getAllTasks_sorted( @RequestParam( defaultValue = "0") int pageNumber,
                                          @RequestParam( defaultValue = "30") int pageSize,
                                          @RequestParam( defaultValue = "id") String sortBy,
                                          @RequestParam( defaultValue = "ASC") String sortDirection ){

        try {

            Sort.Direction direction = sortDirection.equalsIgnoreCase("DESC") ?
                    Sort.Direction.DESC:Sort.Direction.ASC;

            return taskService.getAll_sortedPaged(pageNumber, pageSize, sortBy, direction);


        } catch ( Exception ex ) {
            System.out.println("CONTROLLER ERROR ------------------------------------------ ------");
            System.out.println(ex.getMessage());
        }
        return null;

    }

    @GetMapping("/hello")
    public String getAllTasks_sorted(  ){
        return "HELLO WORLD";

    }


}
