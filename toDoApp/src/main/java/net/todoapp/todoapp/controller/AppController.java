package net.todoapp.todoapp.controller;


import net.todoapp.todoapp.service.ToDoAppService;
import net.todoapp.todoapp.Entity.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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

    /*
    @GetMapping("/todolist")
    public String getToDoList(){


    }

    @PostMapping("/todo")
    public String getTodoById(@RequestBody Todo id){

    }


    @PostMapping("/markascompleted")
    public String markAsComplete(@RequestBody Todo id){

    }


    @PostMapping("/update")
    public String markAsComplete(@RequestBody Todo todo){

    }*/






}
