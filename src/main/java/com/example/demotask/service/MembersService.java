package com.example.demotask.service;

import com.example.demotask.entity.Member;
import com.example.demotask.repository.MembersRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
