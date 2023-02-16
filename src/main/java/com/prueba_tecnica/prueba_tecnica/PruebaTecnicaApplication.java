package com.prueba_tecnica.prueba_tecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PruebaTecnicaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PruebaTecnicaApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PruebaTecnicaApplication.class);
	}
}
