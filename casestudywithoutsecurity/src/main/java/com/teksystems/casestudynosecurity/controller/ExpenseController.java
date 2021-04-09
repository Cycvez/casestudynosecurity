package com.teksystems.casestudynosecurity.controller;

import java.math.BigDecimal;
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

import com.teksystems.casestudynosecurity.dao.ExpenseDao;
import com.teksystems.casestudynosecurity.dao.UserDao;
import com.teksystems.casestudynosecurity.entity.Expense;
import com.teksystems.casestudynosecurity.entity.User;

@Controller
public class ExpenseController {

	  @Autowired
	    UserDao userDao;

	    @Autowired
	    ExpenseDao expenseDao;



	    @GetMapping("addExpense")
	    public String displayExpense(Integer id, Model model) {
	        User user = userDao.findById(id).orElse(null);
	        Expense expense = new Expense();
	        List<Expense> userExpenses = new ArrayList<>();
	        List<Expense> allExpense = expenseDao.findAll();

	        for (Expense e : allExpense) {
	            if (e.getUser() == user) {
	                userExpenses.add(e);
	            }
	        }

	        model.addAttribute("user", user);
	        model.addAttribute("expense", expense);
	        model.addAttribute("expenseList", userExpenses);

	        return "addExpense";
	    }

	    @PostMapping("addExpense")
	    public String addExpense(@Valid Expense expense, BindingResult result, HttpServletRequest request, Model model) {

	        String userId = request.getParameter("userId");
	        String name = request.getParameter("name");
	        String description = request.getParameter("description");
	        BigDecimal amount = new BigDecimal(request.getParameter("amount"));

	        if (result.hasErrors()) {
	            model.addAttribute("expenseList", expenseDao.findAll());
	            return "home";
	        }

	        expense.setName(name);
	        expense.setDescription(description);
	        expense.setAmount(amount);

	        expense.setUser(userDao.findById(Integer.parseInt(userId)).orElse(null));

	        expenseDao.save(expense);

	        return "redirect:/userHome?id=" + userId;
	    }

	    @GetMapping("deleteExpense")
	    public String deleteExpense(HttpServletRequest request) {
	        int id = Integer.parseInt(request.getParameter("id"));
	        Expense currentExpense = expenseDao.findById(id).orElse(null);
	        User user = currentExpense.getUser();
	        expenseDao.deleteById(id);

	        return "redirect:/userHome?id="+user.getId();
	    }

	
	
	
	
}
