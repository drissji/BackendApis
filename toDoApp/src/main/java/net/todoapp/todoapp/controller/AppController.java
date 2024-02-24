package net.todoapp.todoapp.controller;


import net.todoapp.todoapp.Entity.AppUser;
import net.todoapp.todoapp.Entity.Todo;
import net.todoapp.todoapp.repository.ToDoAppRepository;
import net.todoapp.todoapp.repository.UserRepository;
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
    private final ToDoAppRepository toDoAppRepository;

    private final UserRepository userRepository;
    public AppController(ToDoAppService toDoAppService, ToDoAppRepository toDoAppRepository, UserRepository userRepository) {
        this.toDoAppService = toDoAppService;
        this.toDoAppRepository = toDoAppRepository;
        this.userRepository = userRepository;
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

    @GetMapping(path = {"/", "/search"})
    public String getIndexPage(Model model, String keyword){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(keyword!=null) {
            AppUser userName = userRepository.findAppUserByUserName(authentication.getName());
            model.addAttribute("tasks",  toDoAppRepository.findByUserAndTitleContaining(userName,keyword));
        }else {
            model.addAttribute("tasks", toDoAppService.getTodoList(authentication.getName()));}
        return "tasks";
    }

    //Custom login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /*@GetMapping("/register")
    public String register() {
        return "register";
    }*/
/*
    @GetMapping("/yes")
    public String test() {
        System.out.println("teeeeeeeTTTTeeeé");
        return "register";
    }*/

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute("user") AppUser user) {
        System.out.println("djskldfjslkdjf");
        return "register";
    }

    @GetMapping("/insert")
    public String showInsertForm() {
        return "insert_task_form";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute Todo task, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        toDoAppService.addTodo(authentication.getName(), task);
        return "redirect:/";
    }



   /* @PostMapping("/insert")
    public String addTodo(Model model) {
        return "redirect:/insert";
    }*/




   /* @PostMapping("/insert")
    public ResponseEntity<HttpStatus> addTodo(@RequestBody Todo todo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            return toDoAppService.addTodo(authentication.getName(),todo);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


    /*@PostMapping("/insert")
    public String addTodo(@RequestBody Todo todo){
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        try {
            return toDoAppService.addTodo(authentication.getName(),todo);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }*/

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
