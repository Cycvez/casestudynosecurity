package com.teksystems.casestudynosecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.teksystems.casestudynosecurity.dao.UserDao;
import com.teksystems.casestudynosecurity.entity.User;

@Controller
public class LoginController {

	@Autowired
	UserDao userDao;

	@PostMapping("/login")
	public String displayHome(@ModelAttribute("userlogging") User userlogging, Model model) {
		List<User> userlist = userDao.findAll();
		for(User user : userlist) {
			if (userlogging.getUsername().equals(user.getUsername()) && userlogging.getPassword().equals(user.getPassword())){
				model.addAttribute("user",user);
				return "redirect:/userHome?id="+user.getId();
			}
		}


		return "login";
	}

}
