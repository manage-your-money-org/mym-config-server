package com.rkumar0206.mymconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MymConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MymConfigServerApplication.class, args);
	}

}
