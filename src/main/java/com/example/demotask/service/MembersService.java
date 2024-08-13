package com.example.demotask.service;

import com.example.demotask.config.AppConfig;
import com.example.demotask.entity.Book;
import com.example.demotask.entity.Member;
import com.example.demotask.exception.*;
import com.example.demotask.repository.BooksRepository;
import com.example.demotask.repository.MembersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MembersService {
    private final MembersRepository membersRepository;
    private final BooksRepository booksRepository;
    private final AppConfig appConfig;

    public List<Member> getAllMembers() {
        return membersRepository.findAll();
    }

    public Long addMember(Member member) {
        Member savedMember = membersRepository.save(member);
        return savedMember.getId();
    }

    public void updateMember(Long id, Member member) {
        Member memberInDataBase = membersRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Member with id " + id + "not found!"));
        memberInDataBase.setName(member.getName());
        memberInDataBase.setDate(member.getDate());

        membersRepository.save(memberInDataBase);
    }

    public void deleteMember(Long id) {
        Optional<Member> member = membersRepository.findById(id);
        if (member.get().getListOfBooks() != null) {
            throw new MemberCannotBeDeletedException("This member hes borrowed book! Cant be deleted!");
        }
        membersRepository.deleteById(id);
    }

    @Transactional
    public void addBook(Long id, Book book) {

        Member member = membersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Member not found"));

        if (book.getAmount() == 0) {
            throw new ZeroAmountException("All this book was borrow!");
        }

        if (member.getListOfBooks().size() >= appConfig.getMaxBooks()) {
            throw new MaxAmountBookException("You have a lot of books for reading. " +
                    "Max count of book is: " + appConfig.getMaxBooks());
        }

        member.addBook(book);
        book.setAmount(book.getAmount() - 1);

        if (book.getAmount() == 0) {
            book.setBorrowed(true);
        }

        booksRepository.save(book);
        membersRepository.save(member);
    }

    @Transactional
    public void deleteBookInMember(Long id, Book book) {

        Member member = membersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Member not found"));
        Book isPresentBook = booksRepository.findByTitle(book.getTitle());
        if (isPresentBook != null) {
            if (book.isBorrowed()) {
                throw new BookBorrowedException("This book is borrowed!");
            }

            member.removeBook(book);
            book.setAmount(book.getAmount() + 1);
            book.setBorrowed(false);

            booksRepository.save(book);
            membersRepository.save(member);
        } else {
            throw new NotFoundException("Member doesnt have this book!");
        }
    }
}