package net.todoapp.todoapp.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Document
public class AppUser implements UserDetails {

    @MongoId
    ObjectId userId;
    String userName;
    //String userEmail;
    String userPassword;
    List<Roles> userRoles;
    @DBRef
    @JsonManagedReference
    // Annotation to establish a MongoDB database reference
    List<Todo> userTask = new ArrayList<>();


    public AppUser(){

    }
    public AppUser(String userName, String userPassword, List<Roles> userRoles, List<Todo> userTask) {
        this.userId = new ObjectId();
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRoles = userRoles;
        this.userTask = userTask;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<Roles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Roles> userRoles) {
        this.userRoles = userRoles;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public List<Todo> getUserTask() {
        return userTask;
    }

    public void setUserTask(List<Todo> userTask) {
        this.userTask = userTask;
    }


    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    @Override
    public List<Roles> getAuthorities() {
        return userRoles;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
