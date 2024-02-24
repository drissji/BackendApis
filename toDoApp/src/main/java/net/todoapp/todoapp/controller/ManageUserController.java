package net.todoapp.todoapp.controller;

import net.todoapp.todoapp.Entity.AppUser;
import net.todoapp.todoapp.Entity.Todo;
import net.todoapp.todoapp.repository.ToDoAppRepository;
import net.todoapp.todoapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ManageUserController {

    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    public final ToDoAppRepository toDoAppRepository;


    public ManageUserController(UserRepository userRepository, PasswordEncoder passwordEncoder, ToDoAppRepository toDoAppRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.toDoAppRepository = toDoAppRepository;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AppUser user){
        AppUser searchUser =  userRepository.findAppUserByUserName(user.getUserName());
        if(searchUser != null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken. Please try again");
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("saved");
    }




    @GetMapping("/test/{username}/{key}")
    public List<Todo> test(@PathVariable String username,@PathVariable String key){
       AppUser user =  userRepository.findAppUserByUserName(username);
        return toDoAppRepository.findByUserAndTitleContaining(user,key);
    }


}
