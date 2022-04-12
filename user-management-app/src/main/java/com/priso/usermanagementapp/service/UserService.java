package com.priso.usermanagementapp.service;

import java.util.List;

import com.priso.usermanagementapp.dto.User;

public interface UserService {
	public List<User> getAllUsers();

	public void addUser(User user);

	public User getUser(String id);

	public User updateUser(User user);

	public User deleteUser(String id);
}
