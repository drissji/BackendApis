package net.todoapp.todoapp.Entity;


import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document
public class Todo {

    @MongoId
    ObjectId id;
    String title;
    String description;
    Boolean isCompleted;
    Date date;
    Date dueDate;



    public Todo(String title, String description, Boolean isCompleted, Date dueDate) {
        id = new ObjectId();
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        date = new Date();
        this.dueDate = dueDate;
    }
}