package com.cg.ppa.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.cg.ppa.entities.User;
import com.cg.ppa.exception.UserException;

public interface ILoginService {
	User addUser(User user) throws UserException;

	User viewUserById(int userId) throws UserException;

	void deleteUser(int userId) throws UserException;

	User loginUser(String email, String password, HttpSession session) throws UserException;

	User updateUser(User user) throws UserException;

	User viewUserByName(String name) throws UserException;

	List<User> viewAllUser() throws UserException;
	
	void logoutUser(HttpSession session);
	
	User viewUserByEmailId(String emailId) throws UserException;
}
