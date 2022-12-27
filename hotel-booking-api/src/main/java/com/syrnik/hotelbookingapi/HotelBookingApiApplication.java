package com.syrnik.hotelbookingapi;

import com.syrnik.hotelbookingapi.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class HotelBookingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingApiApplication.class, args);
	}

}
