package com.todo_list.todo_list_my_artifact.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EntityService<T,D, ID> {

    T create(T entity) ;
    T createByDto( D dto);
    T findById(ID id);
    List<T> getAll();
    void delete(T entity);
    void deleteById(ID id);
    void update(T entity);
    Page<T> getAll_sortedPaged(int page, int size, String sortBy, Sort.Direction sortDirection);



}
