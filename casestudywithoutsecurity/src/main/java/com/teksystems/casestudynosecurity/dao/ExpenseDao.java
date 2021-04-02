package com.teksystems.casestudynosecurity.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teksystems.casestudynosecurity.entity.Expense;



@Repository
public interface ExpenseDao extends JpaRepository <Expense, Integer>{

}
