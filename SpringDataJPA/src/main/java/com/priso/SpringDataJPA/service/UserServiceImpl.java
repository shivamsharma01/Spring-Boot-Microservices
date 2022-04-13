package com.priso.SpringDataJPA.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.priso.SpringDataJPA.dto.User;
import com.priso.SpringDataJPA.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public Iterable<User> findAllUsers() {
		Iterable<User> userList = userRepository.findAll();
		logger.info("User list from the db - {}", userList);
		return userList;
	}

	@Override
	public void addUser(User user) {
		userRepository.save(user);
		logger.info("User added - {}", user);
	}

	@Override
	public User findUserById(Long id) {
		User user = userRepository.findById(id).orElse(new User());
		logger.info("User from the db - {}", user);
		return user;
	}

	@Override
	public String updateUser(Long id, String address) {
		userRepository.updateAddress(id, address);
		String response = String.format("New address for user_id %d is - %s", id, address);
		logger.info(response);
		return response;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(Long.valueOf(id));
		logger.info("user with {} deleted from the db", id);
	}

	@Override
	public List<User> getAllUsersByFirstName(String firstName) {
		return userRepository.getAllUsersByFirstName(firstName);
	}

	@Override
	public List<User> getAllUsersByGender(String gender) {
		return userRepository.getAllUsersByGender(gender);
	}

	@Override
	public List<User> getAllUsersByGenderAndSort(String gender, String sortingParam) {
		return (List<User>) userRepository.getAllUsersByGenderAndSort(gender, Sort.by(sortingParam));
	}

	@Override
	public List<User> findSortedUsers(String sortingParam) {
		return (List<User>) userRepository.findAll(Sort.by(sortingParam));
	}

}
