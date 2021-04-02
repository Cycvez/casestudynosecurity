package com.teksystems.casestudynosecurity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teksystems.casestudynosecurity.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
