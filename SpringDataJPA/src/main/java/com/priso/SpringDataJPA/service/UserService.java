package com.priso.SpringDataJPA.service;

import java.util.List;

import com.priso.SpringDataJPA.dto.User;

public interface UserService {
	Iterable<User> findAllUsers();

	void addUser(User user);

	User findUserById(Long id);

	String updateUser(Long id, String address);

	void deleteUser(Long id);

	List<User> getAllUsersByFirstName(String firstName);

	List<User> getAllUsersByGender(String gender);

}
