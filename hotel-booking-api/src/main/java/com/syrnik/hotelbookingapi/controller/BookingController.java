package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.dao.BookingDao;
import com.syrnik.hotelbookingapi.dao.RoomDao;
import com.syrnik.hotelbookingapi.dao.UserDao;
import com.syrnik.hotelbookingapi.dto.AvailableRoomDto;
import com.syrnik.hotelbookingapi.dto.BookingRequest;
import com.syrnik.hotelbookingapi.dto.RegisterRequest;
import com.syrnik.hotelbookingapi.dto.ResponseMessage;
import com.syrnik.hotelbookingapi.model.User;
import com.syrnik.hotelbookingapi.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookingController {
    private final BookingDao bookingDao;
    private final RoomDao roomDao;
    private final UserDao userDao;

    @PostMapping("/booking")
    public ResponseEntity<ResponseMessage> makeBooking(@RequestBody BookingRequest bookingRequest) throws SQLException {
        try {
            List<Long> availableRoomIds = roomDao.findAvailableRoomsIdByType(bookingRequest.getHotelId(),
                    bookingRequest.getStartDate(), bookingRequest.getEndDate(), bookingRequest.getRoomTypeId());

            // get currently logged in user
            Long userId;
            Object principal = SecurityContextHolder.getContext().getAuthentication();
            if (principal instanceof JwtAuthenticationToken jwtAuthenticationToken) {
                User user = userDao.findByEmail(jwtAuthenticationToken.getName()).orElseThrow(() -> new Exception("Cannot get logged in user by email"));
                for (int i = 0; i < bookingRequest.getRoomAmount(); i++) {
                    bookingDao.makeReservation(user.getId(), bookingRequest.getStartDate(), bookingRequest.getEndDate(), availableRoomIds.get(i));
                }
            } else {
                throw new Exception("Cannot get logged in user");
            }
            return ResponseEntity.ok(new ResponseMessage("Dokonano rezerwacji"));
        }  catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
