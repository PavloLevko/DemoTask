package com.example.demotask.service;

import com.example.demotask.entity.Book;
import com.example.demotask.exception.NotFoundException;
import com.example.demotask.repository.BooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Book> getById(Long id) {
      return repository.findById(id);
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }

    public Book updateBook(Long id, Book book) {
        Book bookInDataBase = (repository.
                findById(id).orElseThrow(() -> new NotFoundException("Book with id " + id + " not found!")));

        bookInDataBase.setTitle(book.getTitle());
        bookInDataBase.setAuthor(book.getAuthor());
        bookInDataBase.setAmount(book.getAmount());

      return repository.save(bookInDataBase);
    }
}
