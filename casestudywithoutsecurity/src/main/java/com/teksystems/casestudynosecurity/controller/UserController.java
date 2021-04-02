package com.teksystems.casestudynosecurity.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import com.teksystems.casestudynosecurity.dao.UserDao;
import com.teksystems.casestudynosecurity.entity.User;

@Controller
public class UserController {
	
	@Autowired
	UserDao users;
	
	Set<ConstraintViolation<User>> violations = new HashSet<>();
	
	
	@PostMapping("registerUser")
	public String registerUser(@Valid User user, BindingResult result, HttpServletRequest request, Model model) {
		String fName = request.getParameter("fname");
        String lName = request.getParameter("lname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (result.hasErrors()) {
            model.addAttribute("userList", users.findAll());
            return "users";
        }
		
        user.setFname(fName);
        user.setLname(lName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setUsername(username);
        user.setPassword(password);
        
        users.save(user);
		
		return "login";
	}
	
	
	
//	@PostMapping("newUser")
//    public String addUser(String fname, String lname, String username, String email, String phone, String password) {
//        List<User> allUsers = users.findAll();
//        List<String> usernames = new ArrayList<>();
//        for (User currentUser : allUsers) {
//            usernames.add(currentUser.getUsername());
//        }
//            User user = new User();
//            user.setFname(fname);
//            user.setLname(lname);
//            user.setEmail(email);
//            user.setPhone(phone);
//            user.setUsername(username);
////            enable encoder for password when implimenting security
//            user.setPassword(password);
//            users.save(user);
////
////            Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
////            violations = validate.validate(user);
//
////            if (violations.isEmpty()) {
////                users.save(user);
////            }
//        
//        return "redirect:/login";
//    }
}
