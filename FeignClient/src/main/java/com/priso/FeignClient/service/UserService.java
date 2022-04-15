package com.priso.FeignClient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.priso.FeignClient.dto.User;

@FeignClient(name = "${service.name}", url = "${service.url}")
public interface UserService {
	@GetMapping("/user/{id}")
	User getUser(@PathVariable("id") String id);

	@PostMapping("/user")
	String addUser(User user);

	@DeleteMapping("/user/{id}")
	void deleteUser(@PathVariable("id") String id);

	@PutMapping("/user/{id}")
	public String updateUser(@RequestParam("address") String address, @PathVariable("id") Long id);

	@GetMapping("/users")
	public Iterable<User> findAllUsers();
}
