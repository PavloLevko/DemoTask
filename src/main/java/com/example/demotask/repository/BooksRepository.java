package com.example.demotask.repository;

import com.example.demotask.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public interface BooksRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);
}
