package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.dao.CityDao;
import com.syrnik.hotelbookingapi.dao.CountryDao;
import com.syrnik.hotelbookingapi.dto.CityDto;
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

    @GetMapping("/city")
    public ResponseEntity<List<CityDto>> getCitiesByCountryName(@RequestParam String countryName) {
        try {
            List<CityDto> cities = cityDao.findByCountryName(countryName);
            if(cities.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(cities);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}