package com.cg.ppa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ppa.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	public boolean existsByUserId(int userId);

	public User findByUserId(int userId);

	public boolean existsByEmailIdAndPassword(String emailId, String password);

	public User findByEmailIdAndPassword(String emailId, String password);

	public User findByUserName(String userName);

	public boolean existsByUserName(String userName);
	
	public User findByEmailId(String emailId);
	
	public boolean existsByEmailId(String emailId);
}
