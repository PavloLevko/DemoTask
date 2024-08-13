package com.example.demotask.controller;

import com.example.demotask.entity.Book;
import com.example.demotask.entity.Member;
import com.example.demotask.service.MembersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberController {
    private final MembersService membersService;

    @GetMapping("/members")
    public List<Member> getAll() {
        return membersService.getAllMembers();
    }

    @PostMapping
    @RequestMapping("/member")
    public Long addMember(@RequestBody Member member) {
        return membersService.addMember(member);
    }

    @PutMapping("/member/{id}")
    public void updateMember(@PathVariable Long id,
                             @RequestBody Member member) {
        membersService.updateMember(id, member);
    }

    @DeleteMapping("/member/{id}")
    public void deleteMember(@PathVariable Long id) {
        membersService.deleteMember(id);
    }

    @PostMapping
    @RequestMapping("/member/book/{id}")
    public void addBookForMember(@PathVariable Long id,
                                 @RequestBody Book book) {
        membersService.addBook(id, book);
    }

    @DeleteMapping("/member/book/{id}")
    public void deletedBookInMember(@PathVariable Long id,
                                    @RequestBody Book book) {
        membersService.deleteBookInMember(id, book);
    }
}