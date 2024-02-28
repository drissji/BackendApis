package net.spring.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class BlogTags {

    @MongoId
    private Long tagId;

    private String tagName;

    private Byte isDeleted;

    public BlogTags(String tagName) {
        this.tagName = tagName;
    }

}
