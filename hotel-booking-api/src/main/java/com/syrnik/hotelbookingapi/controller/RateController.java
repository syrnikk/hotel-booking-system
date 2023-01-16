package com.syrnik.hotelbookingapi.controller;

import com.syrnik.hotelbookingapi.dao.RateDao;
import com.syrnik.hotelbookingapi.dao.UserDao;
import com.syrnik.hotelbookingapi.dto.ResponseMessage;
import com.syrnik.hotelbookingapi.model.Hotel;
import com.syrnik.hotelbookingapi.model.Rate;
import com.syrnik.hotelbookingapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RateController {
    private final UserDao userDao;
    private final RateDao rateDao;

    @GetMapping("/rate")
    public ResponseEntity<Rate> getRateByHotelId(@RequestParam Long hotelId) {
        try {
            // get currently logged in user
            User user = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication();
            if (principal instanceof JwtAuthenticationToken jwtAuthenticationToken) {
                user = userDao.findByEmail(jwtAuthenticationToken.getName()).orElseThrow(() -> new Exception("Cannot get logged in user by email"));
            }
            Rate rate = rateDao.findByHotelAndUserId(hotelId, user.getId());
            if(rate == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(rate);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/rate")
    public ResponseEntity<ResponseMessage> rate(@RequestBody Rate rate) {
        try {
            // get currently logged in user
            User user = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication();
            if (principal instanceof JwtAuthenticationToken jwtAuthenticationToken) {
                user = userDao.findByEmail(jwtAuthenticationToken.getName()).orElseThrow(() -> new Exception("Cannot get logged in user by email"));
            }
            rate.setUser(User.builder().id(user.getId()).build());
            rateDao.rate(rate);
            return ResponseEntity.ok(new ResponseMessage("Rate added/updated successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
