package com.todo.jpa.service;

import com.todo.jpa.model.Todo;
import com.todo.jpa.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * Created by anand on 27/10/17.
 */
@Service
@Transactional
public class TodoService {


    @Autowired
    private TodoRepository todoRepository;

    /**
     * Save todo.
     *
     * @param  todo data
     * @return the page
     */

    public Optional<Todo> save(Todo todo) {
        if(todo.getId()==null){
            todo.setCreated(new Date());
        }
        Optional<Todo> itemOptional= Optional.ofNullable(todo);
        if (itemOptional.isPresent()) {
            Todo todoItem = todoRepository.save(itemOptional.get());
            itemOptional = Optional.ofNullable(todo);
        }return itemOptional;
    }

    /**
     * Find all page.
     *
     * @param pageable the pageable
     * @return the page
     */
    @Transactional(readOnly = true)
    public Page<Todo> findAll(Pageable pageable) {
        Page<Todo> items = todoRepository.findAll(pageable);
        return items;
    }

    /**
     * Find all todo by status.
     *
     * @param completed is completed or not , pageable the pageable
     * @return the page
     */
    @Transactional(readOnly = true)
    public Page<Todo> getTodoByStatus(Boolean completed,Pageable pageable) {
        Page<Todo> items = todoRepository.getTodoByStatus(completed,pageable);
        return items;
    }

    /**
     * Find one item.
     *
     * @param id the id
     * @return the item
     */
    @Transactional(readOnly = true)
    public Todo findOne(Long id) {
        Todo item = todoRepository.findOne(id.toString());
        return item;
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(Long id) {
        todoRepository.delete(id.toString());
    }

    /**
     * Delete All
     */
    public void deleteAll () {
        todoRepository.deleteAll();
    }
}
