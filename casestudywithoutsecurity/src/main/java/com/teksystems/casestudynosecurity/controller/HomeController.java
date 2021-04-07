package com.teksystems.casestudynosecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.teksystems.casestudynosecurity.dao.UserDao;
import com.teksystems.casestudynosecurity.entity.User;


@Controller
public class HomeController {
	
	@Autowired
	UserDao userDao;
	
    @GetMapping("/")
    public String displayHome(Model model) {
//        User user = new User();
//
//        model.addAttribute("user", user);
//        model.addAttribute("usersList", userDao.findAll());

        return "login";
    }
	
}
