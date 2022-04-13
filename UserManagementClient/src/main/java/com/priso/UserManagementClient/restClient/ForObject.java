package com.priso.UserManagementClient.restClient;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.priso.UserManagementClient.dto.User;

public class ForObject {

	private static Logger logger = LoggerFactory.getLogger(ForObject.class);
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
		useForObjectMethodsOfRestTemplateWithUser();
		useForObjectMethodsOfRestTemplateWithUsers();
	}

	private void useForObjectMethodsOfRestTemplateWithUser() {
		User response = restTemplate.getForObject(String.format("%s/%s/%d/", baseUrl, "user", 22), User.class);
		System.out.println(response);
	}

	private void useForObjectMethodsOfRestTemplateWithUsers() {
		List response = restTemplate.getForObject(String.format("%s/%s", baseUrl, "users"), List.class);
		System.out.println(response);
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
		useForObjectMethodsOfRestTemplateWithUser(user);
	}

	private void useForObjectMethodsOfRestTemplateWithUser(User user) {
		String response = restTemplate.postForObject(String.format("%s/%s", baseUrl, "user"), user, String.class);
		System.out.println(response);
	}

}
