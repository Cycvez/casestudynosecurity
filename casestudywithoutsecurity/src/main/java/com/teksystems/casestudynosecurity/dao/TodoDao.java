package com.teksystems.casestudynosecurity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teksystems.casestudynosecurity.entity.Todo;



@Repository
public interface TodoDao extends JpaRepository<Todo, Integer>{
List<Todo> findAll();
	
	
}
