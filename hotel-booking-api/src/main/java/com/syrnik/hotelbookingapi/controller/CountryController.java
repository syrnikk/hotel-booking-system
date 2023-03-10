package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.dao.CountryDao;
import com.syrnik.hotelbookingapi.dto.ResponseMessage;
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
    public ResponseEntity<List<Country>> getCountries() {
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

    @GetMapping("/country/{id}")
    public ResponseEntity<Country> findCountryById(@PathVariable Long id) {
        try {
            Country country = countryDao.findById(id);
            if(country == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(country);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/country/{id}")
    public ResponseEntity<ResponseMessage> updateCountryById(@PathVariable Long id, @RequestBody Country country) {
        try {
            countryDao.updateById(id, country);
            return ResponseEntity.ok(new ResponseMessage("Country updated successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/country")
    public ResponseEntity<ResponseMessage> addCountry(@RequestBody Country country) {
        try {
            countryDao.save(country);
            return ResponseEntity.ok(new ResponseMessage("Country added successfully"));
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

    @DeleteMapping("/country/{id}")
    public ResponseEntity<ResponseMessage> deleteCountry(@PathVariable Long id) {
        try {
            countryDao.deleteById(id);
            return ResponseEntity.ok(new ResponseMessage("Country deleted successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

