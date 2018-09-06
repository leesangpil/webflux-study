package my.study.thymeleaf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorld {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
