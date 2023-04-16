package com.hescha.rudictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/word";
    }

    @GetMapping("/login")
    public String admin(){
        return "login";
    }

    @GetMapping("/search")
    public String searchpage(){
        return "search_result";
    }
}
