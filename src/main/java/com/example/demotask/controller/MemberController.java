package com.example.demotask.controller;

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
    public List<Member> getAll(){
        return membersService.getAllMembers();
    }
    @PostMapping
    @RequestMapping("/member")
    public Long addMember(@RequestBody Member member){
    return membersService.addMember(member);
    }
}
