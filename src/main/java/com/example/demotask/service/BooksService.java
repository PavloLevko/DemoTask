package com.example.demotask.service;

import com.example.demotask.entity.Book;
import com.example.demotask.repository.BooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BooksService {
    private final BooksRepository repository;


    public List<Book> getAllBooks() {
     return repository.findAll();
    }

    public Long addBook(Book book) {
        Book savedBook = repository.save(book);
        return savedBook.getId();
    }
}
