package com.sbaldass.booksstore.dtos;

import com.sbaldass.booksstore.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private int yearLaunched;
    private boolean borrowed;
    private Long userId;
    private LocalDate borrowedAt;
    private LocalDate returnedAt;
}
