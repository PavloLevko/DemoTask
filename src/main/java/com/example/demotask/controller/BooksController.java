package com.example.demotask.controller;

import com.example.demotask.entity.Book;
import com.example.demotask.service.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("api/books")

public class BooksController {
    private final BooksService booksService;
    @GetMapping
    public List<Book> getAllBook(){
   return booksService.getAllBooks();
    }
    @PostMapping
    @RequestMapping("/book")
    public Long addBook(@RequestBody Book book){
        return booksService.addBook(book);
    }
}
