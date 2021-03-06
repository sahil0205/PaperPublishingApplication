package com.cg.ppa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ppa.entities.User;
import com.cg.ppa.exception.UserException;
import com.cg.ppa.service.ILoginService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ppa/user")
public class UserController {

	@Autowired
	ILoginService service;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/adduser")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		try {
			User userData = service.addUser(user);
			return new ResponseEntity<Object>(userData, HttpStatus.OK);
		} catch (UserException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@PutMapping("/updateuser")
	public ResponseEntity<Object> updateuser(@Valid @RequestBody User user) {
		try {
			User userData = service.updateUser(user);
			return new ResponseEntity<Object>(userData, HttpStatus.OK);
		} catch (UserException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable int userId) {
		try {
			service.deleteUser(userId);
			return new ResponseEntity<Object>("User Deleted", HttpStatus.OK);
		} catch (UserException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/viewuserbyid/{userId}")
	public ResponseEntity<Object> viewUserById(@PathVariable int userId) {
		try {
			User userData = service.viewUserById(userId);
			return new ResponseEntity<Object>(userData, HttpStatus.OK);
		} catch (UserException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/loginuser")
	public ResponseEntity<Object> loginUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
		try {
			User userData = service.loginUser(email, password, session);
			return new ResponseEntity<Object>(userData, HttpStatus.OK);
		} catch (UserException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/viewallusers")
	public ResponseEntity<Object> viewAllUsers() {
		try {
			List<User> userList = service.viewAllUser();
			return new ResponseEntity<Object>(userList, HttpStatus.OK);
		} catch (UserException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/viewuserbyname/{userName}")
	public ResponseEntity<Object> viewUserbyName(@PathVariable String userName) {
		try {
			User userData = service.viewUserByName(userName);
			return new ResponseEntity<Object>(userData, HttpStatus.OK);
		} catch (UserException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<Object> logoutUser(HttpSession session){
		try {
			service.logoutUser(session);
			logger.info("Inside signout");
			return new ResponseEntity<Object>("Logout succesfull", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>("Logout unsuccesfull", HttpStatus.BAD_REQUEST);
		}
	}
}
