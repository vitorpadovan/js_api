package com.br.pcsemdor.jsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class JsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsApiApplication.class, args);
	}

}
