package com.example.formation;

import org.springframework.boot.SpringApplication;

public class TestFormationApplication {

	public static void main(String[] args) {
		SpringApplication.from(FormationApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
