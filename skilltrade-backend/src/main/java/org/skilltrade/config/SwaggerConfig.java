package org.skilltrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title("Skill trading platform")
			.description("Backend APIs Skill trading platform")
			.version("v1.0.0")
			.contact(new Contact().name("Abhisek Nayak").url("#").email("abhiseknayak84@gmail.com"))
			.license(new License().name("License").url("/")))
			.externalDocs(new ExternalDocumentation().description("Skill trade platform for all")
			.url("http://localhost:7428/swagger-ui/index.html"));
	}
	
}