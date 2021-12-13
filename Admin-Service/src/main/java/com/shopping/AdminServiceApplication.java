package com.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServiceApplication.class, args);
	
			}
			@Bean
			public RestTemplate getRestTemplate()
			{
				return new RestTemplate();
			}
			public static final String ADMIN_TAG = "admin service";
			@Bean
			public Docket swaggerConfiguration() {
				return new Docket(DocumentationType.SWAGGER_2)
						.select()
						.paths(PathSelectors.ant("/admin/**"))
						.apis(RequestHandlerSelectors.basePackage("com.shopping"))
						.build()
						.tags(new Tag(ADMIN_TAG, "the admin API with description api tag"));
					
				
			}
		
}
