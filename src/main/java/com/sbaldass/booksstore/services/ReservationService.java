package com.sbaldass.booksstore.services;
import com.sbaldass.booksstore.dtos.ReservationDTO;
import com.sbaldass.booksstore.models.Book;
import com.sbaldass.booksstore.models.Reservation;
import com.sbaldass.booksstore.models.ReservationStatus;
import com.sbaldass.booksstore.models.User;
import com.sbaldass.booksstore.repositories.BookRepository;
import com.sbaldass.booksstore.repositories.ReservationRepository;
import com.sbaldass.booksstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ReservationDTO createReservation(ReservationDTO reservationDTO) throws Exception{
        Book book = bookRepository.findById(reservationDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (!book.isAvailable() && !isBookAlreadyReserved(reservationDTO.getBookId())) {
            Reservation reservation = convertToEntity(reservationDTO);

            reservation.setReservationDate(LocalDate.now());
            reservation.setStatus(ReservationStatus.RESERVADO);

            Reservation savedReservation = reservationRepository.save(reservation);
            return convertToDTO(savedReservation);
        } else {
            throw new RuntimeException("Livro não está disponível para reserva ou já está reservado");
        }
    }

    @Transactional
    public ReservationDTO cancelReservation(Long reservationId) throws Exception{
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reservation.setStatus(ReservationStatus.DISPONIVEL);
        Reservation updatedReservation = reservationRepository.save(reservation);
        return convertToDTO(updatedReservation);
    }

    private boolean isBookAlreadyReserved(Long bookId) {
        List<Reservation> reservations = reservationRepository.findByBookIdAndStatus(bookId, ReservationStatus.RESERVADO);
        return !reservations.isEmpty();
    }

    private Reservation convertToEntity(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setBook(bookRepository.findById(reservationDTO.getBookId()).orElseThrow(() -> new RuntimeException("Livro não encontrado.")));
        reservation.setUser(userRepository.findById(reservationDTO.getUserId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado.")));
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setStatus(reservationDTO.getStatus());
        return reservation;
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setBookId(reservation.getBook().getId());
        reservationDTO.setUserId(reservation.getUser().getId());
        reservationDTO.setReservationDate(reservation.getReservationDate());
        reservationDTO.setStatus(reservation.getStatus());
        return reservationDTO;
    }


}
