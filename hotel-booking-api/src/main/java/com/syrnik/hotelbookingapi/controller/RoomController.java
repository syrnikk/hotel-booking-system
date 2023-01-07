package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.dao.RoomDao;
import com.syrnik.hotelbookingapi.dto.AvailableRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomController {
    private final RoomDao roomDao;

    @GetMapping("/room")
    public ResponseEntity<List<AvailableRoomDto>> getAvailablesRoom(@RequestParam Long hotelId,
            @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        try {
            List<AvailableRoomDto> availableRooms = roomDao.findAvailableRoomsGroupByType(hotelId, startDate, endDate);
            if(availableRooms.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(availableRooms);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
