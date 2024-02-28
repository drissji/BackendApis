package net.spring.blog.controller.admin;


import net.spring.blog.entity.Blog;
import net.spring.blog.entity.BlogTags;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/")
    public String getIndexPage(){
        return "/admin/index";
    }

    @GetMapping("/blogs/edit")
    public String edit(){

        return "/admin/blogs/edit";

    }

    @PostMapping("/blogs/edit")
    public String editBlog(@RequestParam("blogTitle") String blogTitle,@RequestParam("blogTagsList") String tags){

        ArrayList<BlogTags> blogTags = new ArrayList<>();
        List<String> tagsList = Arrays.asList(tags.split("\\s*,\\s*"));
        for (String tag : tagsList){
            BlogTags blogTags1 = new BlogTags(tag);
            blogTags.add(blogTags1);
        }

        Blog blog = new Blog();
        blog.setBlogTitle(blogTitle);
        blog.setBlogTagsList(blogTags);

        System.out.println(blog.getBlogId());
        System.out.println(blog.getBlogTitle());
        System.out.println(blog.getBlogTagsList());


        return "/admin/blogs/edit";

    }


}
