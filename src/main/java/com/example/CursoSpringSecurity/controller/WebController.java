package com.example.CursoSpringSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class WebController {

    @GetMapping("/admin_page")
    public String only_admin() {
        return "<h1>admin page<h1>";
    }

    @GetMapping("/user_page")
    public String user_page() {
        return "<h1>user page<h1>";
    }

}
