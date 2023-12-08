package com.sbaldass.booksstore.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;

    private String title;

    private String author;

    private int yearPublished;

    private boolean available;
}
