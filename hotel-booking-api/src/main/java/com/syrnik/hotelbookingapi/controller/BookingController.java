package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.dao.BookingDao;
import com.syrnik.hotelbookingapi.dao.UserDao;
import com.syrnik.hotelbookingapi.dto.BookingRequest;
import com.syrnik.hotelbookingapi.dto.ReservationDetailsDto;
import com.syrnik.hotelbookingapi.dto.ResponseMessage;
import com.syrnik.hotelbookingapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookingController {
    private final BookingDao bookingDao;
    private final UserDao userDao;

    @PostMapping("/booking")
    public ResponseEntity<ResponseMessage> makeBooking(@RequestBody BookingRequest bookingRequest) throws SQLException {
        try {
            // get currently logged in user
            Object principal = SecurityContextHolder.getContext().getAuthentication();
            if (principal instanceof JwtAuthenticationToken jwtAuthenticationToken) {
                User user = userDao.findByEmail(jwtAuthenticationToken.getName()).orElseThrow(() -> new Exception("Cannot get logged in user by email"));
                bookingDao.makeReservation(user.getId(), bookingRequest);
            } else {
                throw new Exception("Cannot get logged in user");
            }
            return ResponseEntity.ok(new ResponseMessage("Dokonano rezerwacji"));
        }  catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/booking")
    public ResponseEntity<List<ReservationDetailsDto>> getBookings() {
        try {
            // get currently logged in user
            User user = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication();
            if (principal instanceof JwtAuthenticationToken jwtAuthenticationToken) {
                user = userDao.findByEmail(jwtAuthenticationToken.getName()).orElseThrow(() -> new Exception("Cannot get logged in user by email"));
            }
            List<ReservationDetailsDto> reservations = bookingDao.getReservations(user.getId());
            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
