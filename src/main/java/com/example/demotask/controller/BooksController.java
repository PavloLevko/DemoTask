package com.example.demotask.controller;

import com.example.demotask.entity.Book;
import com.example.demotask.service.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api")

public class BooksController {
    private final BooksService booksService;

    @GetMapping("/books")
    public List<Book> getAllBook() {
        return booksService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return booksService.getById(id);
    }

    @PostMapping
    @RequestMapping("/book")
    public Long addBook(@RequestBody Book book) {
        return booksService.addBook(book);
    }

    @PutMapping("/book/{id}")
    public Book updateBook(@PathVariable Long id,
                           @RequestBody Book book) {
        return booksService.updateBook(id, book);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBookById(@PathVariable Long id) {
        booksService.deleteBook(id);
    }
}