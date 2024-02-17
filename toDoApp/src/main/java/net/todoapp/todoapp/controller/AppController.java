package net.todoapp.todoapp.controller;


import net.todoapp.todoapp.Entity.Todo;
import net.todoapp.todoapp.service.ToDoAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController
//@RequestMapping("/tasks")
public class AppController {

    private final ToDoAppService toDoAppService;
    public AppController(ToDoAppService toDoAppService) {
        this.toDoAppService = toDoAppService;
    }

    /*@GetMapping("/")
    public String getIndexPage(Model model){
        //model.addAttribute("index", new Index());
        model.addAttribute("tasks",toDoAppService.getTodoList());
        return "index";
    }*/

    /*@GetMapping("/findtitle")
    public String findTitle(Model model, @RequestParam("term") String term){
        model.addAttribute("tasks",toDoAppService.findTitleContaining(term));
        return "index";
        //return toDoAppService.findTitleContaining(userIdterm);
    }*/

    @GetMapping(path = {"/","/search"})
    public String getIndexPage(Model model, String keyword){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(keyword!=null) {
            model.addAttribute("tasks",  toDoAppService.findTitleContaining(keyword,authentication.getName()));
        }else {
            model.addAttribute("tasks", toDoAppService.getTodoList(authentication.getName()));}
        return "tasks";
    }

    @RequestMapping("/insert")
    public String addTodo(){
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        try {
            return toDoAppService.addTodo(authentication.getName(),todo);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
       return "insert_task_form";

    }

    @GetMapping("/tasks")
    public List<Todo> getTask(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());

        return toDoAppService.getTodoList(authentication.getName());

    }


/*


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
    }*/

    /*@GetMapping("/todolist")
    public void getToDoList(Model model){
            model.addAttribute("tasks",toDoAppService.getTodoList());
    }*/

    /*
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

    @GetMapping("/sort")
    public List<Todo> getSortedTodo(){
       return toDoAppService.getSortedTodoList();
    }



    @GetMapping("/findtodo/{title}/{status}")
    public List<Todo> findTodo(@PathVariable String title,@PathVariable boolean status){
        return toDoAppService.findTodo(title,status);
    }*/


}
