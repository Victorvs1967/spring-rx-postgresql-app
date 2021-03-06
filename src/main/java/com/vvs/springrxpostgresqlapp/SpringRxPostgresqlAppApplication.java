package com.vvs.springrxpostgresqlapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@SpringBootApplication
@EnableReactiveMongoAuditing
public class SpringRxPostgresqlAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRxPostgresqlAppApplication.class, args);
	}

}
