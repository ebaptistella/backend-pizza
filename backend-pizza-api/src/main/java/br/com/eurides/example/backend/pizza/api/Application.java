package br.com.eurides.example.backend.pizza.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan("br.com.eurides.example.backend.pizza.repository.domain")
@EnableJpaRepositories("br.com.eurides.example.backend.pizza.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = { "br.com.eurides.example.backend.pizza" })
@PropertySource(ignoreResourceNotFound = false, value = "classpath:backend-pizza-${spring.profiles.active}.properties")
@PropertySource(ignoreResourceNotFound = false, value = "classpath:application-${spring.profiles.active}.properties")
@PropertySource(ignoreResourceNotFound = false, value = "classpath:application.properties")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
