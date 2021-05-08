package com.cg.ppa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ppa.entities.User;
import com.cg.ppa.exception.UserException;
import com.cg.ppa.repository.IUserRepository;

@Service
@Transactional
public class LoginService implements ILoginService {

	@Autowired
	IUserRepository repository;

	@Override
	public User addUser(User user) throws UserException {
		if (repository.existsByUserId(user.getUserId()))
			throw new UserException("User already exists");
		else {
			User userData = repository.save(user);
			return userData;
		}
	}

	@Override
	public User viewUserById(int userId) throws UserException {
		if (repository.existsByUserId(userId)) {
			User userData = repository.findByUserId(userId);
			return userData;
		} else
			throw new UserException("No User found");
	}

	@Override
	public void deleteUser(int userId) throws UserException {
		if (repository.existsByUserId(userId))
			repository.deleteById(userId);
		else
			throw new UserException("No User found");
	}

	@Override
	public User loginUser(String email, String password) throws UserException {
		if (repository.existsByEmailIdAndPassword(email, password)) {
			User userData = repository.findByEmailIdAndPassword(email, password);
			return userData;
		} else
			throw new UserException("Invalid Credentials");
	}

	@Override
	public User updateUser(User user) throws UserException {
		if (repository.existsByUserId(user.getUserId())) {
			User userData = repository.save(user);
			return userData;
		} else
			throw new UserException("No User found");
	}

	@Override
	public User viewUserByName(String name) throws UserException {
		if (repository.existsByUserName(name)) {
			User userData = repository.findByUserName(name);
			return userData;
		} else
			throw new UserException("No User found");
	}

	@Override
	public List<User> viewAllUser() throws UserException {
		List<User> userList = repository.findAll();
		if (userList.isEmpty())
			throw new UserException("No Users present");
		else
			return userList;
	}

}
