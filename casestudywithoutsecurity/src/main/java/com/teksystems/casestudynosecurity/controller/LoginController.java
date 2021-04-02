package com.teksystems.casestudynosecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.teksystems.casestudynosecurity.dao.UserDao;

@Controller
public class LoginController {
	
	@Autowired
	UserDao userDao;
	
	
    @GetMapping("/register")
    public String registrationForm() {
    	return "register";
    }
}
