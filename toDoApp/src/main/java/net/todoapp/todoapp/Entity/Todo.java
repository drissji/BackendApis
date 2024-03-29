package net.todoapp.todoapp.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @DBRef
    @JsonBackReference
    AppUser user;



    public Todo(String title, String description, Boolean isCompleted, Date dueDate, AppUser user) {
        id = new ObjectId();
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        date = new Date();
        this.dueDate = dueDate;
        this.user = user;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public ObjectId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public Date getDueDate() {
        return dueDate;
    }
}
