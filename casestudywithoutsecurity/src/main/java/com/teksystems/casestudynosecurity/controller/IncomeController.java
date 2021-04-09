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

import com.teksystems.casestudynosecurity.dao.IncomeDao;
import com.teksystems.casestudynosecurity.dao.UserDao;
import com.teksystems.casestudynosecurity.entity.Income;
import com.teksystems.casestudynosecurity.entity.User;

@Controller
public class IncomeController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	IncomeDao incomeDao;
	
	Set<ConstraintViolation<User>> violations = new HashSet<>();
	
	@GetMapping("addIncome")
	public String addIncome(Integer id, Model model) {
		User user = userDao.findById(id).orElse(null);
        Income income = new Income();
        List<Income> userIncomes = new ArrayList<>();
        List<Income> allIncomes = incomeDao.findAll();
        
        for (Income i : allIncomes) {
            if (i.getUser() == user) {
                userIncomes.add(i);
            }
        }
        
        model.addAttribute("user", user);
        model.addAttribute("income", income);
        model.addAttribute("incomeList", userIncomes);
        
        return "addIncome";
    }
    
    @PostMapping("addIncome")
    public String addIncome(@Valid Income income, BindingResult result, HttpServletRequest request, Model model) {

        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        
        if (result.hasErrors()) {
            model.addAttribute("incomeList", incomeDao.findAll());
            return "addIncome";
        }
        
        income.setName(name);
        income.setDescription(description);
        income.setAmount(amount);
        income.setUser(userDao.findById(Integer.parseInt(userId)).orElse(null));
        
        incomeDao.save(income);
        
        return "redirect:/userHome?id="+userId;
    }
    
    @GetMapping("deleteIncome")
    public String deleteSuper(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Income currentIncome=incomeDao.findById(id).orElse(null);
        User user = currentIncome.getUser();
        incomeDao.deleteById(id);
        
        return "redirect:/userHome?id="+user.getId();
    }
}
