package com.example.demotask.entity;

import com.example.demotask.validation.ValidName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ValidName
    private String name;
    private LocalDate date;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> listOfBooks = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        date = LocalDate.now();
    }

    public void addBook(Book book) {
        listOfBooks.add(book);
        book.setMember(this);
    }

    public void removeBook(Book book) {
        listOfBooks.remove(book);
        book.setMember(null);
    }
}