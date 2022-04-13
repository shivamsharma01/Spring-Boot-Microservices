package com.priso.UserManagementClient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.priso.UserManagementClient.dto.User;
import com.priso.UserManagementClient.restClient.Exchange;
import com.priso.UserManagementClient.restClient.ForEntity;
import com.priso.UserManagementClient.restClient.ForObject;

@RestController
public class RestControllerClient {

	@Autowired
	private Exchange exchange;
	@Autowired
	private ForEntity forEntity;
	@Autowired
	private ForObject forObject;
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/test")
	public void test() {
		exchange.run();
		forEntity.run();
		forObject.run();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/test2")
	public List<User> test2() {
		return restTemplate.getForObject("users", List.class);
	}
}
