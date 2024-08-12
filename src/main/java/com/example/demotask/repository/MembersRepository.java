package com.example.demotask.repository;

import com.example.demotask.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Member, Long> {
}
