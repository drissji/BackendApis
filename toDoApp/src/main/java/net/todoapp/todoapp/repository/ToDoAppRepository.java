package net.todoapp.todoapp.repository;

import net.todoapp.todoapp.Entity.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoAppRepository extends MongoRepository<Todo,String> {

}
