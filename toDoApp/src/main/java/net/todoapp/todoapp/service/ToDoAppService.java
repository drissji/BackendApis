package net.todoapp.todoapp.service;

import net.todoapp.todoapp.Entity.AppUser;
import net.todoapp.todoapp.Entity.Todo;
import net.todoapp.todoapp.repository.ToDoAppRepository;
import net.todoapp.todoapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ToDoAppService {

    private final ToDoAppRepository toDoAppRepository;
    private final UserRepository userRepository;

    public ToDoAppService(ToDoAppRepository toDoAppRepository, UserRepository userRepository) {
        this.toDoAppRepository = toDoAppRepository;
        this.userRepository = userRepository;
    }

    //Add ToDo To DB
    public ResponseEntity<HttpStatus> addTodo(String userName,Todo todo){

        AppUser user = userRepository.findAppUserByUserName(userName);

        List<Todo> userTasks = user.getUserTask();
        userTasks.add(todo);
        userRepository.save(user);
        toDoAppRepository.save(todo);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    //Delete TODO
    public ResponseEntity<String> deleteTodo(@PathVariable ObjectId id){

        Optional<Todo> todo = toDoAppRepository.findById(id.toString());
        todo.ifPresent(todo1 -> {
            toDoAppRepository.delete(todo1);
        });

        return ResponseEntity.ok().body("DELETED");
    }

    //Get TODO LIST
    public List<Todo> getTodoList(String userName){

        AppUser user = userRepository.findAppUserByUserName(userName);
        return user.getUserTask();

       /* List<Todo> todoList = toDoAppRepository.findAll();
         if (todoList.isEmpty()){
             throw new NoSuchElementException("ToDo List Empty");
         }
         return todoList;*/
    }

    //GET TODO BY id
    public Optional<Optional<Todo>> getToDoById(ObjectId id){
        Optional<Todo> todo = toDoAppRepository.findById(id.toString());
        return Optional.of(todo);
    }

    //Modify TODO Status

    /*
    public Optional<Todo> modifyStatus(ObjectId id, boolean status){
        Optional<Todo> todo = toDoAppRepository.findById(id.toString());
        todo.ifPresent(todo1 ->
               {
                   todo1.setCompleted(status);
                   addTodo(todo1);
               }
               );
        return todo;
    }*/

    //Modify TODO

/*
    public void modifyTodo(ObjectId id, String... params) {
        Optional<Todo> todo = toDoAppRepository.findById(id.toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0; i < params.length; i++){
            int index = i;
            todo.ifPresent(todo1 -> {
                switch (index) {
                    case 0:
                        todo1.setTitle(params[index]);
                        break;
                    case 1:
                        todo1.setDescription(params[index]);
                        break;
                    case 2:
                        todo1.setCompleted(Boolean.parseBoolean(params[index]));
                        break;
                    case 3:
                        try {
                            todo1.setDueDate(dateFormat.parse(params[index]));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                }
                addTodo(todo1);
            });
        }
    }*/


    //Modify TODO
    /*public void modifyTodo(ObjectId id, String title, String description,boolean status, Date date) {

        Optional<Todo> todo = toDoAppRepository.findById(id.toString());
        todo.ifPresent(todo1 ->
                {
                    todo1.setTitle(title);
                    todo1.setDescription(description);
                    todo1.setCompleted(status);
                    todo1.setDueDate(date);
                    addTodo(todo1);
                }
        );

    }*/

    //SORT TODO
    public List<Todo> getSortedTodoList(){
       return toDoAppRepository.findAll(Sort.by(Sort.Direction.ASC,"dueDate"));
    }


    //Find Containing a word
    public List<Todo> findTitleContaining(String term){
       return toDoAppRepository.findByTitleContaining(term);
    }

    public List<Todo> findTodo(String title,boolean status){
        return toDoAppRepository.findByTitleAndIsCompleted(title,status);
    }



}
