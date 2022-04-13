package com.priso.UserManagementClient.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplateHandler;

@Configuration
public class Config {

	private static final String baseUrl = "http://localhost:8082/springDataDemo/";

	@Bean
	public RestTemplate getRestTemplateBean(RestTemplateBuilder builder) {
		UriTemplateHandler templatehandler = new RootUriTemplateHandler(baseUrl);
		return builder.uriTemplateHandler(templatehandler).setReadTimeout(Duration.ofMillis(2000)).build();
	}
}
