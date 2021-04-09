package com.teksystems.casestudynosecurity.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.teksystems.casestudynosecurity.dao.TodoDao;
import com.teksystems.casestudynosecurity.dao.UserDao;
import com.teksystems.casestudynosecurity.entity.Todo;
import com.teksystems.casestudynosecurity.entity.User;

@Controller
public class TodoController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	TodoDao todoDao;
	
	Set<ConstraintViolation<User>> violations = new HashSet<>();
	
	@GetMapping("addTodo")
	public String addIncome(Integer id, Model model) {
		User user = userDao.findById(id).orElse(null);
        Todo todo= new Todo();
        List<Todo> userTodo= new ArrayList<>();
        List<Todo> allTodo = todoDao.findAll();
        
        for (Todo t : allTodo) {
            if (t.getUser() == user) {
            	userTodo.add(t);
            }
        }
        
        model.addAttribute("user", user);
        model.addAttribute("todo", todo);
        model.addAttribute("todoList", userTodo);
        
        return "addTodo";
    }
	
	@PostMapping("addTodo")
    public String addIncome(@Valid Todo todo, BindingResult result, HttpServletRequest request, Model model) {

        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        // by default the completed is false 
        Boolean completed = false;
        
        if (result.hasErrors()) {
            model.addAttribute("todoList", todoDao.findAll());
            return "home";
        }
        
        todo.setName(name);
        todo.setDescription(description);
        todo.setCompleted(completed);
        todo.setUser(userDao.findById(Integer.parseInt(userId)).orElse(null));
        
        todoDao.save(todo);
        
        return "redirect:/userHome?id="+userId;
    }
}
