package net.spring.blog.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.annotation.processing.Generated;
import java.util.List;

@Document
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class User {

   @MongoId
    private Long id;
    private String username;
    private String Password;

   private List<Blog> blogs;


}
