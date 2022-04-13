package com.priso.UserManagementClient.restClient;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.priso.UserManagementClient.dto.User;

public class ForEntity {

	private static RestTemplate restTemplate = new RestTemplate();
	private static final String baseUrl = "http://localhost:8082/springDataDemo";

	public void run() {
		try {
			getCalls();
		} catch (IOException e) {
			e.printStackTrace();
		}
		postCalls();
	}

	private void getCalls() throws IOException {
		useExchangeMethodsOfRestTemplateWithUser();
		useExchangeMethodsOfRestTemplateWithUsers();
	}

	private void useExchangeMethodsOfRestTemplateWithUser() {
		ResponseEntity<User> responseEntity = restTemplate.getForEntity(String.format("%s/%s/%d/", baseUrl, "user", 22),
				User.class);
		printResponse(responseEntity);
	}

	private void useExchangeMethodsOfRestTemplateWithUsers() {
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
		useExchangeMethodsOfRestTemplateWithUser(user);
	}

	private void useExchangeMethodsOfRestTemplateWithUser(User user) {
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(String.format("%s/%s", baseUrl, "user"),
				user, String.class);
		printResponse(responseEntity);
	}

}
