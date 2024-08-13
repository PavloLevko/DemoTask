package com.example.demotask.service;

import com.example.demotask.entity.Book;
import com.example.demotask.entity.Member;
import com.example.demotask.exception.NotFoundException;
import com.example.demotask.repository.MembersRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MembersService {
    private final MembersRepository repository;

    public List<Member> getAllMembers() {
      return repository.findAll();
    }

    public Long addMember(Member member) {
        Member savedMember = repository.save(member);
        return savedMember.getId();
    }

    public void deleteMember(Long id) {
        repository.deleteById(id);
    }
@Transactional
    public void addBook(Long id, Book book) {
        Optional<Member> memberById = repository.findById(id);
        if(memberById.isPresent()){
            memberById.get().addBook(book);
            book.setAmount(book.getAmount() - 1);
            book.setBorrowed(true);
            repository.save(memberById.get());
        }else {
            throw new NotFoundException("Member not found");
        }
    }
}
