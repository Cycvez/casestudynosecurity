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

import com.teksystems.casestudynosecurity.dao.ExpenseDao;
import com.teksystems.casestudynosecurity.dao.IncomeDao;
import com.teksystems.casestudynosecurity.dao.PostsDao;
import com.teksystems.casestudynosecurity.dao.TodoDao;
import com.teksystems.casestudynosecurity.dao.UserDao;
import com.teksystems.casestudynosecurity.entity.Expense;
import com.teksystems.casestudynosecurity.entity.Income;
import com.teksystems.casestudynosecurity.entity.Posts;
import com.teksystems.casestudynosecurity.entity.Todo;
import com.teksystems.casestudynosecurity.entity.User;

@Controller
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
    ExpenseDao expenseDao;

    @Autowired
    PostsDao postsDao;
    
    @Autowired
    IncomeDao incomeDao;

    @Autowired
    TodoDao todoDao;
	
	Set<ConstraintViolation<User>> violations = new HashSet<>();
   
	@GetMapping("/register")
    public String registrationForm() {
    	return "register";
    }
	
	@PostMapping("/registerUser")
	public String registerUser(@Valid User user, BindingResult result, HttpServletRequest request, Model model) {
		String fName = request.getParameter("fname");
        String lName = request.getParameter("lname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (result.hasErrors()) {
            return "registerUser";
        }
		
        user.setFirstname(fName);
        user.setLastname(lName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setUsername(username);
        user.setPassword(password);
        
        userDao.save(user);
		
		return "login";
	}
	
	
	@GetMapping("userHome")
    public String selectedUser(Integer id, Model model) {
        User user = userDao.findById(id).orElse(null);
        List<Income> userIncomes = new ArrayList<>();
        List<Income> allIncomes = incomeDao.findAll();

        for (Income i : allIncomes) {
            if (i.getUser() == user) {
                userIncomes.add(i);
            }
        }
        List<Expense> userExpenses = new ArrayList<>();
        List<Expense> allExpenses = expenseDao.findAll();
        for (Expense e : allExpenses) {
            if (e.getUser() == user) {
                userExpenses.add(e);
            }
        }
        BigDecimal budget = new BigDecimal("0.00");

        for (Income eachIncome : userIncomes) {
            BigDecimal currentIncome = eachIncome.getAmount();
            budget = budget.add(currentIncome);
        }

        for (Expense eachExpense : userExpenses) {
            BigDecimal currentExpense = eachExpense.getAmount();
            budget = budget.subtract(currentExpense);
        }

        List<Todo> userTodo = new ArrayList<>();
        List<Todo> allTodo= todoDao.findAll();
        for (Todo t : allTodo) {
            if (t.getUser()== user) {
            	userTodo.add(t);
            }
        }
        
        List<Posts> userPosts= new ArrayList<>();
        List<Posts> allPosts= postsDao.findAll();

        for (Posts p: allPosts) {
            if (p.getUser() == user) {
            	userPosts.add(p);
            }
        }

        model.addAttribute("user", user);
        model.addAttribute("budget", budget);
        model.addAttribute("incomeList", userIncomes);
        model.addAttribute("expenseList", userExpenses);
        model.addAttribute("userTodo", userTodo);
        model.addAttribute("userPosts", userPosts);

        return "userHome";
    }
	
	

}
