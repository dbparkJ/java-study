package com.example.fileuploadtest.controller;


import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public String mainPage() {
        return "index";
    }
}
