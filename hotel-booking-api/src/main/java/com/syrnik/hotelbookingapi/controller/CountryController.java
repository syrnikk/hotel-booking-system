package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.dao.CountryDao;
import com.syrnik.hotelbookingapi.model.Country;
import com.syrnik.hotelbookingapi.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CountryController {
    private final CountryDao countryDao;

    @GetMapping("/country")
    public ResponseEntity<List<Country>> getRoles() {
        try {
            List<Country> countries = countryDao.find();
            if(countries.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(countries);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/country")
    public ResponseEntity<String> addRole(@RequestBody Country country) {
        try {
            countryDao.save(country);
            return ResponseEntity.ok("Country added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/country/{id}")
    public ResponseEntity<String> getRoles(@PathVariable Long id) {
        try {
            countryDao.deleteById(id);
            return ResponseEntity.ok("Country deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

