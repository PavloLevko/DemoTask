package com.example.demotask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
public class BooksController {
    @GetMapping
    public String getBook(){
        return "book";
    }
}
