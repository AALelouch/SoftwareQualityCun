package com.config.serverclientfilm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ServerClientFilmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerClientFilmApplication.class, args);
	}

}
