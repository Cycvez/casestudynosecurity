package com.teksystems.casestudynosecurity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teksystems.casestudynosecurity.entity.ToDo;

public interface ToDoDao extends JpaRepository<ToDo, Integer>{

	
	
}
