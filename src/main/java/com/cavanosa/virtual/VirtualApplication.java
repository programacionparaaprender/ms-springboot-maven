package com.cavanosa.virtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
@EnableCaching
public class VirtualApplication {

	public static void main(String[] args) {
		log.info("Funciona log4j");
		SpringApplication.run(VirtualApplication.class, args);
	}

}
