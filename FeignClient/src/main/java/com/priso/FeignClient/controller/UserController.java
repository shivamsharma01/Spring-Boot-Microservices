package com.priso.FeignClient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.priso.FeignClient.dto.User;
import com.priso.FeignClient.service.UserService;

@RestController
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") String id) {
		logger.info("Calling getUser to get user with id {}", id);
		return userService.getUser(id);
	}

	@PostMapping("/user")
	public String addUser(@RequestBody User user) {
		logger.info("Calling addUser to add user {}", user);
		return userService.addUser(user);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") String id) {
		userService.deleteUser(id);
	}

	@PutMapping("/user/{id}")
	public String updateUser(@RequestParam("address") String address, @PathVariable("id") Long id) {
		return userService.updateUser(address, id);
	}
	
	@GetMapping("/userList")
	public Iterable<User> findAllUsers() {
		return userService.findAllUsers();
	}

}
