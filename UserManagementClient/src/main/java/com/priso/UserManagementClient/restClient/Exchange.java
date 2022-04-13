package com.priso.UserManagementClient.restClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.priso.UserManagementClient.dto.User;

public class Exchange {

	private static RestTemplate restTemplate = new RestTemplate();
	private static final String baseUrl = "http://localhost:8082/springDataDemo";
	private static final HttpHeaders headers;

	static {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	public void run() {
		try {
			getCalls();
		} catch (IOException e) {
			e.printStackTrace();
		}
		postCalls();
	}

	private void printResponse(ResponseEntity<?> responseEntity) {
		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());
		System.out.println(responseEntity.getHeaders());
	}

	private void getCalls() throws IOException {
		useExchangeMethodsOfRestTemplateWithObject();
		useExchangeMethodsOfRestTemplateWithUser();
		sendGET();
		useExchangeMethodsOfRestTemplateWithUsers();
	}

	private void useExchangeMethodsOfRestTemplateWithObject() {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(String.format("%s/%s/%d/", baseUrl, "user", 22),
				HttpMethod.GET, requestEntity, String.class);
		printResponse(responseEntity);
	}

	private void useExchangeMethodsOfRestTemplateWithUser() {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		ResponseEntity<User> responseEntity = restTemplate.exchange(String.format("%s/%s/%d/", baseUrl, "user", 22),
				HttpMethod.GET, requestEntity, User.class);
		printResponse(responseEntity);
	}

	private void useExchangeMethodsOfRestTemplateWithUsers() {
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		ResponseEntity<List> responseEntity = restTemplate.exchange(String.format("%s/%s", baseUrl, "users"),
				HttpMethod.GET, requestEntity, List.class);
		printResponse(responseEntity);
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
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(user, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(String.format("%s/%s", baseUrl, "user"),
				HttpMethod.POST, requestEntity, String.class);
		printResponse(responseEntity);
	}

	private void sendGET() throws IOException {

		URL obj = new URL(String.format("%s/%s/%d/", baseUrl, "user", 22));
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code : " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}

	}
}
