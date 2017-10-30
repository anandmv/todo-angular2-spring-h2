package com.todo.jpa.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import com.todo.jpa.controller.util.HeaderUtil;
import com.todo.jpa.controller.util.PaginationUtil;
import com.todo.jpa.model.Todo;
import com.todo.jpa.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@RequestMapping(path="/todo", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> findAll(@RequestParam(value="status", required = false, defaultValue = "") String status,Pageable page) throws IOException, URISyntaxException {
		Page<Todo> todoPages =  todoService.findAll(page);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(todoPages, "/api/todo");
        return new ResponseEntity<>(todoPages.getContent(), headers, HttpStatus.OK);
	}

    @RequestMapping(path="/todo/list/completed/{status}", method = RequestMethod.GET)
    public ResponseEntity<List<Todo>> findAllByStatus(@PathVariable Boolean status,Pageable page) throws IOException, URISyntaxException {
        Page<Todo> todoPages =  todoService.getTodoByStatus(status,page);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(todoPages, "/api/todo");
        return new ResponseEntity<>(todoPages.getContent(), headers, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/todo", method = RequestMethod.POST)
	public ResponseEntity<Todo> saveRepo(@RequestBody Todo todo) throws URISyntaxException {
		Todo todoItem = todoService.save(todo).get();
		return ResponseEntity.created(new URI("/api/todo/" + todoItem.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("todo", todo.getId().toString()))
				.body(todoItem);
	}

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Todo todo = todoService.findOne(id);
        return Optional.ofNullable(todo)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id,@RequestBody Todo todo) throws URISyntaxException {
        todo = todoService.save(todo).get();
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("/api/todo", todo.getId().toString()))
                .body(todo);
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("todo", id.toString())).build();
    }

    @RequestMapping(
            value = "/api/todo/**",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
