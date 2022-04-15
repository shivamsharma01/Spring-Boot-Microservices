package com.priso.SpringDataJPA.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.priso.SpringDataJPA.dto.User;
import com.priso.SpringDataJPA.service.UserService;

@RestController
public class UserController {

	Logger log = LoggerFactory.getLogger("UserManagementApp");
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public Iterable<User> findAllUsers() {
		log.info("getting users...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userService.findAllUsers();
	}

	@PostMapping("/users")
	public void addAllUsers(@RequestBody List<User> users) {
		log.info("adding users...");
		userService.addAllUsers(users);
	}

	@DeleteMapping("/users")
	public void deleteAllUsers() {
		log.info("deleting users...");
		userService.deleteAllUsers();
	}

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") Long id) {
		log.info(String.format("Requesting user with id %d", id));
		return userService.findUserById(id);
	}

	@PostMapping("/user")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		userService.addUser(user);
		URI path = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(path).build();
	}

	@PutMapping("/user/{id}")
	public String updateUser(@RequestParam("address") String address, @PathVariable("id") Long id) {
		return userService.updateUser(id, address);
	}

	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return "User with Id " + id + " deleted";
	}

	@GetMapping("/getAllUsersByFirstName/{firstName}")
	public List<User> getAllUsersByFirstName(@PathVariable("firstName") String firstName) {
		return userService.getAllUsersByFirstName(firstName);
	}

	@GetMapping("/getAllUsersByGender/{gender}")
	public List<User> getAllUsersByGender(@PathVariable("gender") String gender) {
		return userService.getAllUsersByGender(gender);
	}

	@GetMapping("/getAllSortedUsers/{sortingParam}")
	public List<User> getAllSortedUsers(@PathVariable("sortingParam") String sortingParam) {
		return userService.getAllSortedUsers(sortingParam);
	}

	@GetMapping("/getAllSortedUsersByGender/{gender}/{sortingParam}")
	public List<User> getAllUsersByGenderAndSort(@PathVariable("gender") String gender,
			@PathVariable("sortingParam") String sortingParam) {
		return userService.getAllUsersByGenderAndSort(gender, sortingParam);
	}

	@GetMapping("/getAllPagedUsers/{pageNumber}/{noOfElements}")
	public Page<User> getAllPagedUsers(@PathVariable("pageNumber") String pageNumber,
			@PathVariable("noOfElements") String noOfElements) {
		return userService.getAllPagedUsers(Integer.parseInt(pageNumber), Integer.parseInt(noOfElements));
	}

}
