package com.sbaldass.booksstore.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int yearLaunched;

    @CreationTimestamp
    @Column(updatable = false, name = "borrowed_at")
    private LocalDate borrowedAt;

    @UpdateTimestamp
    @Column(name = "returned_at")
    private LocalDate returnedAt;

    private boolean borrowed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User borrowedBy;
}