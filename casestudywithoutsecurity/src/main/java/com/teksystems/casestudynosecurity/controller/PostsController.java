package com.teksystems.casestudynosecurity.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.teksystems.casestudynosecurity.dao.PostsDao;
import com.teksystems.casestudynosecurity.dao.UserDao;
import com.teksystems.casestudynosecurity.entity.Posts;
import com.teksystems.casestudynosecurity.entity.User;

@Controller
public class PostsController {

	
	@Autowired
	PostsDao postsDao;
	@Autowired
	UserDao userDao;
	
	@GetMapping("addPosts")
	public String addIncome(Integer id, Model model) {
		User user = userDao.findById(id).orElse(null);
        Posts posts= new Posts();
        List<Posts> userPosts= new ArrayList<>();
        List<Posts> allPosts= postsDao.findAll();
        
        for (Posts p : allPosts) {
            if (p.getUser() == user) {
            	userPosts.add(p);
            }
        }
        
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("userPosts", userPosts);
        
        return "addPosts";
    }
	
    @PostMapping("addPosts")
    public String addPostsSubmit(@Valid Posts posts, BindingResult result, HttpServletRequest request, Model model) {

        String userId = request.getParameter("userId");
        String body = request.getParameter("body");
        LocalDate createdDate = LocalDate.parse(request.getParameter("createdDate"));
        
        if (result.hasErrors()) {
            model.addAttribute("incomeList", postsDao.findAll());
            return "home";
        }
        
        posts.setBody(body);
        posts.setCreatedDate(createdDate);
        posts.setUser(userDao.findById(Integer.parseInt(userId)).orElse(null));
        
        postsDao.save(posts);
        
        return "redirect:/userHome?id="+userId;
    }
}
