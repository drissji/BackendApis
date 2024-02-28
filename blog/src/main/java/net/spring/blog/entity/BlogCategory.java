package net.spring.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class BlogCategory {

    @MongoId
    private Long categoryId;
    private String categoryName;
    private String categoryIcon;

    private Integer categoryRank;

    private Byte isDeleted;



}
