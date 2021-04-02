package com.teksystems.casestudynosecurity.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teksystems.casestudynosecurity.entity.Posts;
import com.teksystems.casestudynosecurity.entity.User;

@Repository
public interface PostsDao extends JpaRepository<Posts, Integer>{

}
