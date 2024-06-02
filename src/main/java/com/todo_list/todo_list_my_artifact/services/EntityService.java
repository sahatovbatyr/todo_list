package com.todo_list.todo_list_my_artifact.services;

import java.util.List;

public interface EntityService<T, ID> {

    void create(T entity) ;
    T findById(ID id);
    List<T> getAll();
    void delete(T entity);
    void update(T entity);



}
