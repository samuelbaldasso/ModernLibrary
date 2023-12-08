package com.sbaldass.booksstore.repositories;

import com.sbaldass.booksstore.models.Reservation;
import com.sbaldass.booksstore.models.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByBookIdAndStatus(Long bookId, ReservationStatus reservationStatus);
}
