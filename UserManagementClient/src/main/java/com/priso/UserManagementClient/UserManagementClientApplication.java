package com.priso.UserManagementClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.priso.UserManagementClient.dto.User;

//@SpringBootApplication
public class UserManagementClientApplication {

	private static RestTemplate restTemplate = new RestTemplate();
	private static final String baseUrl = "http://localhost:8082/springDataDemo";

	public static void main(String[] args) throws IOException {
		// SpringApplication.run(UserManagementClientApplication.class, args);
		useExchangeMethodsOfRestTemplateWithObject();
		useExchangeMethodsOfRestTemplateWithUser();
		sendGET();
	}

	private static void useExchangeMethodsOfRestTemplateWithObject() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(String.format("%s/%s/%d/", baseUrl, "user", 22),
				HttpMethod.GET, requestEntity, String.class);
		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());
		System.out.println(responseEntity.getHeaders());
	}

	private static void useExchangeMethodsOfRestTemplateWithUser() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		ResponseEntity<User> responseEntity = restTemplate.exchange(String.format("%s/%s/%d/", baseUrl, "user", 22),
				HttpMethod.GET, requestEntity, User.class);
		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());
		System.out.println(responseEntity.getHeaders());
	}

	private static void sendGET() throws IOException {

		URL obj = new URL(String.format("%s/%s/%d/", baseUrl, "user", 22));
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
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
