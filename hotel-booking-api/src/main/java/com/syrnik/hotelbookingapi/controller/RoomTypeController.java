package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.dao.RoomTypeDao;
import com.syrnik.hotelbookingapi.dto.ResponseMessage;
import com.syrnik.hotelbookingapi.model.Country;
import com.syrnik.hotelbookingapi.model.RoomType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomTypeController {
    private final RoomTypeDao roomTypeDao;

    @GetMapping("/room-type")
    public ResponseEntity<List<RoomType>> getRoomTypes() {
        try {
            List<RoomType> roomTypes = roomTypeDao.find();
            if(roomTypes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(roomTypes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/room-type")
    public ResponseEntity<ResponseMessage> addRoomType(@RequestBody RoomType roomType) {
        try {
            roomTypeDao.save(roomType);
            return ResponseEntity.ok(new ResponseMessage("Room type added successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @PostMapping("/country/{id}")
//    public ResponseEntity<String> addCountry(@RequestBody Country country) {
//        try {
//            countryDao.save(country);
//            return ResponseEntity.ok("Country added successfully");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @DeleteMapping("/room-type/{id}")
    public ResponseEntity<ResponseMessage> deleteRoomType(@PathVariable Long id) {
        try {
            roomTypeDao.deleteById(id);
            return ResponseEntity.ok(new ResponseMessage("Room type deleted successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
