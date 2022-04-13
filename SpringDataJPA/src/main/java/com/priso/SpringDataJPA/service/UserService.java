package com.priso.SpringDataJPA.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.priso.SpringDataJPA.dto.User;

public interface UserService {
	Iterable<User> findAllUsers();

	void addUser(User user);

	User findUserById(Long id);

	String updateUser(Long id, String address);

	void deleteUser(Long id);

	List<User> getAllUsersByFirstName(String firstName);

	List<User> getAllUsersByGender(String gender);

	List<User> getAllUsersByGenderAndSort(String gender, String sortingParam);

	List<User> getAllSortedUsers(String sortingParam);
	
	Page<User> getAllPagedUsers(int pageNumber, int noOfElementsInAPage);

	void addAllUsers(List<User> users);

	void deleteAllUsers();

}
