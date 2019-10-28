package org.gl.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootAngularJs2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAngularJs2Application.class, args);
	}

}
