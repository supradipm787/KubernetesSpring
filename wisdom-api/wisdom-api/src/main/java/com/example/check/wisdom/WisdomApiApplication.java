// border wisdom brown

package com.example.check.wisdom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WisdomApiApplication {

	public static void main(String[] args) {
		System.out.println("DB_PORT: " + System.getenv("DB_PORT"));
		SpringApplication.run(WisdomApiApplication.class, args);
	}

}
