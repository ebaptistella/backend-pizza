package br.com.eurides.example.backend.pizza.api.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class DocumentationConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.eurides.example.backend.pizza.api.controller"))
				.paths(PathSelectors.any()).build().produces(getAllProduceContentTypes())
				.consumes(getAllConsumeContentTypes());
	}

	private Set<String> getAllConsumeContentTypes() {
		Set<String> consumes = new HashSet<String>();

		consumes.add(MediaType.APPLICATION_JSON_VALUE);
		consumes.add(MediaType.APPLICATION_XML_VALUE);
		return consumes;
	}

	private Set<String> getAllProduceContentTypes() {
		Set<String> produces = new HashSet<String>();

		produces.add(MediaType.APPLICATION_JSON_VALUE);
		produces.add(MediaType.APPLICATION_XML_VALUE);
		return produces;
	}
}
