package com.priso.UserManagementClient.restClient;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.priso.UserManagementClient.dto.User;

public class ForEntity {
	
	private static Logger logger = LoggerFactory.getLogger(ForEntity.class);
	private static RestTemplate restTemplate = new RestTemplate();
	private static final String baseUrl = "http://localhost:8082/springDataDemo";

	public void run() {
		logger.info("Running methods of ForEntity class");
		try {
			postCalls();
			getCalls();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getCalls() throws IOException {
		useForEntityMethodsOfRestTemplateWithUser();
		useForEntityMethodsOfRestTemplateWithUsers();
	}

	private void useForEntityMethodsOfRestTemplateWithUser() {
		ResponseEntity<User> responseEntity = restTemplate.getForEntity(String.format("%s/%s/%d/", baseUrl, "user", 22),
				User.class);
		printResponse(responseEntity);
	}

	private void useForEntityMethodsOfRestTemplateWithUsers() {
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(String.format("%s/%s", baseUrl, "users"),
				List.class);
		printResponse(responseEntity);
	}

	private void printResponse(ResponseEntity<?> responseEntity) {
		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());
		System.out.println(responseEntity.getHeaders());
	}

	private void postCalls() {
		addUser();
	}

	private void addUser() {
		User user = new User();
		user.setFirstName("Management");
		user.setLastName("User");
		user.setAddress("Microservice");
		user.setGender("Male");
		useForEntityMethodsOfRestTemplateWithUser(user);
	}

	private void useForEntityMethodsOfRestTemplateWithUser(User user) {
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(String.format("%s/%s", baseUrl, "user"),
				user, String.class);
		printResponse(responseEntity);
	}

}
