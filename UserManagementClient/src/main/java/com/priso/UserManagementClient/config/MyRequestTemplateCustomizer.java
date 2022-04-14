package com.priso.UserManagementClient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

public class MyRequestTemplateCustomizer implements RestTemplateCustomizer {

	@Autowired
	private MyRequestInterceptor myRequestInterceptor;

	@Override
	public void customize(RestTemplate restTemplate) {
		restTemplate.getInterceptors().add(myRequestInterceptor);
	}

}
