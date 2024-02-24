package net.spring.blog.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/login")
public class AdminController {

    @GetMapping("/test")
    public String test(){
        return "/admin/sidebar";
    }


}
