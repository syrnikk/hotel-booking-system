package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.dao.CityDao;
import com.syrnik.hotelbookingapi.dao.CountryDao;
import com.syrnik.hotelbookingapi.dto.CityDto;
import com.syrnik.hotelbookingapi.dto.ResponseMessage;
import com.syrnik.hotelbookingapi.model.City;
import com.syrnik.hotelbookingapi.model.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CityController {
    private final CityDao cityDao;

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCitiesByCountryName(@RequestParam String countryName) {
        try {
            List<City> cities = cityDao.findByCountryName(countryName);
            if(cities.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(cities);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/city")
    public ResponseEntity<List<City>> getCities() {
        try {
            List<City> cities = cityDao.find();
            if(cities.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(cities);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/city")
    public ResponseEntity<ResponseMessage> addCity(@RequestBody City city) {
        try {
            cityDao.save(city);
            return ResponseEntity.ok(new ResponseMessage("City added successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<ResponseMessage> deleteCountry(@PathVariable Long id) {
        try {
            cityDao.deleteById(id);
            return ResponseEntity.ok(new ResponseMessage("City deleted successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<City> findCityById(@PathVariable Long id) {
        try {
            City city = cityDao.findById(id);
            if(city == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(city);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<ResponseMessage> updateCityById(@PathVariable Long id, @RequestBody City city) {
        try {
            cityDao.updateById(id, city);
            return ResponseEntity.ok(new ResponseMessage("City updated successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}