package com.todo.jpa.repository;

import com.todo.jpa.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TodoRepository extends JpaRepository<Todo, String> {

    @Query("select d from Todo d where d.completed =?1 ")
    Page<Todo> getTodoByStatus(Boolean status, Pageable pageable);
}