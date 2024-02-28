package net.spring.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;


@Document
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Blog {

    @MongoId
    private Long blogId;
    private String blogTitle;
    private String blogContent;
    private ArrayList<BlogTags> blogTagsList;
    private ArrayList<BlogCategory> blogCategoryList;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
