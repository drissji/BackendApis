package net.todoapp.todoapp.service;

import net.todoapp.todoapp.Entity.Todo;
import net.todoapp.todoapp.repository.ToDoAppRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ToDoAppService {

    private final ToDoAppRepository toDoAppRepository;

    public ToDoAppService(ToDoAppRepository toDoAppRepository) {
        this.toDoAppRepository = toDoAppRepository;
    }

    //Add ToDo To DB
    public ResponseEntity<HttpStatus> addTodo(Todo todo){
        toDoAppRepository.save(todo);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }


}
