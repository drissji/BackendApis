package net.todoapp.todoapp.repository;

import net.todoapp.todoapp.Entity.AppUser;
import net.todoapp.todoapp.Entity.Todo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<AppUser,String> {


     AppUser findAppUserByUserName(String uerName);

     //List<AppUser> findByUserTask_Id(ObjectId usrName);
}
