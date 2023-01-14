package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.dao.RoomDao;
import com.syrnik.hotelbookingapi.dto.AvailableRoomDto;
import com.syrnik.hotelbookingapi.dto.ResponseMessage;
import com.syrnik.hotelbookingapi.model.City;
import com.syrnik.hotelbookingapi.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomController {
    private final RoomDao roomDao;

    @GetMapping("/available-rooms")
    public ResponseEntity<List<AvailableRoomDto>> getAvailableRooms(@RequestParam Long hotelId,
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

    @GetMapping("/room")
    public ResponseEntity<List<Room>> getRooms() {
        try {
            List<Room> rooms = roomDao.find();
            if(rooms.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(rooms);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/room")
    public ResponseEntity<ResponseMessage> addRoom(@RequestBody Room room) {
        try {
            roomDao.save(room);
            return ResponseEntity.ok(new ResponseMessage("Room added successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<ResponseMessage> deleteRoom(@PathVariable Long id) {
        try {
            roomDao.deleteById(id);
            return ResponseEntity.ok(new ResponseMessage("Room deleted successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<Room> findRoomById(@PathVariable Long id) {
        try {
            Room room = roomDao.findById(id);
            if(room == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/room/{id}")
    public ResponseEntity<ResponseMessage> updateRoomById(@PathVariable Long id, @RequestBody Room room) {
        try {
            roomDao.updateById(id, room);
            return ResponseEntity.ok(new ResponseMessage("Room updated successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
