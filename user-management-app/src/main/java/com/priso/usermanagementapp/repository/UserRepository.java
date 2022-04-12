package com.priso.usermanagementapp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.priso.usermanagementapp.dto.User;

@Repository
public class UserRepository {

	List<User> userList = new ArrayList<>();

	public List<User> getAllUsers() {
		return userList;
	}

	public User getUser(String id) {
		return userList.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(new User());
	}

	public void addUser(User user) {
		userList.add(user);
	}

	public User updateUser(User user) {
		User first = userList.stream().filter(u -> u.getId().equals(user.getId())).findFirst().orElse(new User());
		if (first.getId().equals(user.getId())) {
			first.setName(user.getName());
			first.setAddress(user.getAddress());
		}
		return first;
	}

	public User deleteUser(String id) {
		User first = userList.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(new User());
		userList.remove(first);
		return first;
	}

}
