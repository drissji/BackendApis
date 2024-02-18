package net.todoapp.todoapp.repository;

import net.todoapp.todoapp.Entity.AppUser;
import net.todoapp.todoapp.Entity.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ToDoAppRepository extends MongoRepository<Todo,String> {

     List<Todo> findByTitleContaining(String word);
     List<Todo> findByTitleAndIsCompleted(String title,boolean completed);

     List<Todo> findByUserAndTitleContaining(AppUser userName,String key);

}
