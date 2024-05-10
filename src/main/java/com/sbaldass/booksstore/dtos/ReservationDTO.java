package com.sbaldass.booksstore.dtos;

import com.sbaldass.booksstore.models.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Long id;
    private Long bookId;
    private Long userId;
    private LocalDate reservationDate;
    private ReservationStatus status;

}
