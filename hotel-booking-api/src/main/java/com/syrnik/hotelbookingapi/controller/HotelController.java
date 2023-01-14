package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.dao.HotelDao;
import com.syrnik.hotelbookingapi.dto.CityDto;
import com.syrnik.hotelbookingapi.dto.ResponseMessage;
import com.syrnik.hotelbookingapi.model.City;
import com.syrnik.hotelbookingapi.model.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotelController {
    private final HotelDao hotelDao;

    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> getHotelsByCityName(@RequestParam String cityName) {
        try {
            List<Hotel> hotels = hotelDao.findByCityName(cityName);
            if(hotels.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(hotels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/hotel")
    public ResponseEntity<List<Hotel>> getHotels() {
        try {
            List<Hotel> hotels = hotelDao.find();
            if(hotels.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(hotels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        try {
            Hotel hotel = hotelDao.findById(id);
            if(hotel == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(hotel);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/hotel")
    public ResponseEntity<ResponseMessage> addHotel(@RequestBody Hotel hotel) {
        try {
            hotelDao.save(hotel);
            return ResponseEntity.ok(new ResponseMessage("Hotel added successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/hotel/{id}")
    public ResponseEntity<ResponseMessage> updateHotelById(@PathVariable Long id, @RequestBody Hotel hotel) {
        try {
            hotelDao.updateById(id, hotel);
            return ResponseEntity.ok(new ResponseMessage("Hotel updated successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/hotel/{id}")
    public ResponseEntity<ResponseMessage> deleteCountry(@PathVariable Long id) {
        try {
            hotelDao.deleteById(id);
            return ResponseEntity.ok(new ResponseMessage("Hotel deleted successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
