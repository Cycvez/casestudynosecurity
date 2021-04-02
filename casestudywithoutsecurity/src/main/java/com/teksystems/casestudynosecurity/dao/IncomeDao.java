package com.teksystems.casestudynosecurity.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teksystems.casestudynosecurity.entity.Income;
import com.teksystems.casestudynosecurity.entity.User;


@Repository
public interface IncomeDao extends JpaRepository <Income, Integer>  {

	List<Income> findAllByUser(User user);
}
