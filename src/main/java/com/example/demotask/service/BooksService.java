package com.example.demotask.service;

import com.example.demotask.entity.Book;
import com.example.demotask.entity.Member;
import com.example.demotask.exception.BookCannotBeDeletedException;
import com.example.demotask.exception.NotFoundException;
import com.example.demotask.repository.BooksRepository;
import com.example.demotask.repository.MembersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BooksService {
    private final BooksRepository booksRepository;
    private final MembersRepository membersRepository;


    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    @Transactional
    public Long addBook(Book book) {
        Book isPresent = booksRepository.findByTitle(book.getTitle());
        if (isPresent != null) {
            isPresent.setAmount(isPresent.getAmount() + 1);
            return booksRepository.save(isPresent).getId();
        } else {
            book.setAmount(1);
            return booksRepository.save(book).getId();
        }
    }

    public Optional<Book> getById(Long id) {
        return booksRepository.findById(id);
    }

    public void deleteBook(Long id) {
        Book isPresentBook = booksRepository.
                findById(id).orElseThrow(() -> new NotFoundException("Book with id " + id + " not found!"));

        if (isPresentBook.getAmount() > 1) {
            isPresentBook.setAmount(isPresentBook.getAmount() - 1);
            booksRepository.save(isPresentBook);
        } else {
            if (isPresentBook.isBorrowed()) {
                throw new BookCannotBeDeletedException("Book is borrowed and can't be deleted.");
            }
            booksRepository.deleteById(id);
        }
    }

    public Book updateBook(Long id, Book book) {
        Book bookInDataBase = (booksRepository.
                findById(id).orElseThrow(() -> new NotFoundException("Book with id " + id + " not found!")));

        bookInDataBase.setTitle(book.getTitle());
        bookInDataBase.setAuthor(book.getAuthor());
        bookInDataBase.setAmount(book.getAmount());

        return booksRepository.save(bookInDataBase);
    }

    public List<Book> getAllBooksByMember(String name) {
        Member memberInDataBase = (Member) membersRepository.findByName(name);
        if(memberInDataBase == null){
            throw new NotFoundException("Member with name: " + name + "not found.");
        }else {
         return memberInDataBase.getListOfBooks();
        }

    }
}