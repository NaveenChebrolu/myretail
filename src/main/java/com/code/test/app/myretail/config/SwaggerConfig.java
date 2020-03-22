package com.code.test.app.myretail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


/**
 * 
 * @author naveen
 *
 */

@EnableSwagger2WebMvc
@Configuration
//@Import({SpringDataRestConfiguration.class})
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.code.test.app.myretail.controller"))
				.paths(PathSelectors.any()).build();
	}
}
