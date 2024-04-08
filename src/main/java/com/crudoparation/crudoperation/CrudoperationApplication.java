package com.crudoparation.crudoperation;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudoperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudoperationApplication.class, args);
	}
	@Bean
	public ModelMapper moddleMapper(){
		return new ModelMapper();
	}
}
