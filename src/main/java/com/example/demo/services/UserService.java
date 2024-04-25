package com.example.demo.services;

import com.example.demo.models.User;

public interface UserService {
	User findByUserName(String userName);
	Boolean create(User user);
}
