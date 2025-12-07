package com.example.kakeibo.repository;

import com.example.kakeibo.model.IncomeExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeExpenseRepository extends JpaRepository<IncomeExpense, Long> {

    List<IncomeExpense> findByMonth(String month);
}
