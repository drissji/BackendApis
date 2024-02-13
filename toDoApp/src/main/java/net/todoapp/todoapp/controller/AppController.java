package net.todoapp.todoapp.controller;


import net.todoapp.todoapp.service.ToDoAppService;
import net.todoapp.todoapp.Entity.Todo;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AppController {


    private final ToDoAppService toDoAppService;

    public AppController(ToDoAppService toDoAppService) {
        this.toDoAppService = toDoAppService;
    }

    @PostMapping("/insert")
    public ResponseEntity<HttpStatus> addTodo(@RequestBody Todo todo){
        try {
            return toDoAppService.addTodo(todo);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable ObjectId id){
        return toDoAppService.deleteTodo(id);
    }

    @GetMapping("/todolist")
    public List<Todo> getToDoList(){
            return toDoAppService.getTodoList();
    }

    @GetMapping("/todo/{id}")
    public  Optional<Optional<Todo>> getTodoById(@PathVariable ObjectId id){
        return toDoAppService.getToDoById(id);
    }

    @PostMapping("/completed/{id}/{status}")
    public Optional<Todo> markAsComplete(@PathVariable ObjectId id, @PathVariable boolean status){
        return toDoAppService.modifyStatus(id,status);
    }

    @PutMapping("/update/{id}")
    public void markAsComplete(@PathVariable ObjectId id, @RequestBody Todo todo){
        toDoAppService.modifyTodo(id,todo.getTitle(),todo.getDescription(),todo.getCompleted(),todo.getDueDate());
    }



}
