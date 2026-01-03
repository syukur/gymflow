package com.lylastudio.gymflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GymflowApplication {


    /**just comment*/
	public static void main(String[] args) {
		SpringApplication.run(GymflowApplication.class, args);
	}

}
