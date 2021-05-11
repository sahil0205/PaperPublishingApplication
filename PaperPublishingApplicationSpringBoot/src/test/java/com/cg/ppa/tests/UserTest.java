package com.cg.ppa.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.ppa.entities.User;
import com.cg.ppa.repository.IUserRepository;
import com.cg.ppa.service.LoginService;

@SpringBootTest
public class UserTest {

	@Autowired
	LoginService service;

	@MockBean
	IUserRepository repository;

	@Test
	public void addUser() {
		User user = new User(1, "User 1", "Editor", "+91 123456789", "user1@gmail.com", "User1#123");
		when(repository.save(user)).thenReturn(user);
		assertEquals(user.getUserId(), service.addUser(user).getUserId());
		assertEquals(user.getUserName(), service.addUser(user).getUserName());
	}

	@Test
	public void viewAllUser() {
		when(repository.findAll()).thenReturn(Stream
				.of(new User(1, "Mock User 1", "Reporter", "+91 7709161413", "mockuser1@gmail.com", "User1@12345"),
						new User(2, "Mock User 2", "Editor", "+91 7709161413", "mockuser2@gmail.com", "user2@12345"))
				.collect(Collectors.toList()));
		assertEquals(2, service.viewAllUser().size());
	}

	@Test
	public void viewUserById() {
		User user = new User(1, "Mock User 1", "Reporter", "+91 7709161413", "mockuser1@gmail.com", "User1@12345");
		when(repository.existsByUserId(1)).thenReturn(true);
		when(repository.findByUserId(1)).thenReturn(user);
		assertEquals(user.getUserId(), service.viewUserById(1).getUserId());
	}

	@Test
	public void deleteUser() {
		when(repository.existsByUserId(1)).thenReturn(true);
		service.deleteUser(1);
		verify(repository, times(1)).deleteById(1);
	}

	@Test
	public void loginUser() {
		User user = new User(1, "Mock User 1", "Reporter", "+91 7709161413", "mockuser1@gmail.com", "User1@12345");
		when(repository.existsByEmailIdAndPassword(user.getEmailId(), user.getPassword())).thenReturn(true);
		when(repository.findByEmailIdAndPassword(user.getEmailId(), user.getPassword())).thenReturn(user);
		assertEquals(1, service.loginUser(user.getEmailId(), user.getPassword()).getUserId());
		assertEquals("Mock User 1", service.loginUser(user.getEmailId(), user.getPassword()).getUserName());
	}

	@Test
	public void viewByName() {
		User user = new User(1, "Mock User 1", "Reporter", "+91 7709161413", "mockuser1@gmail.com", "User1@12345");
		when(repository.existsByUserName(user.getUserName())).thenReturn(true);
		when(repository.findByUserName(user.getUserName())).thenReturn(user);
		assertEquals("Mock User 1", service.viewUserByName("Mock User 1").getUserName());
	}

	@Test
	public void updateUser() {
		User user = new User(1, "Mock User 1", "Reporter", "+91 7709161413", "mockuser1@gmail.com", "User1@12345");
		when(repository.existsByUserId(user.getUserId())).thenReturn(true);
		when(repository.save(user)).thenReturn(user);
		assertEquals(1, service.updateUser(user).getUserId());
	}
}
