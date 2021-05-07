package com.cg.ppa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.ppa.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

}
