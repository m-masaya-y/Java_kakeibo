package com.example.kakeibo.repository;

import com.example.kakeibo.model.IncomeExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeExpenseRepository extends JpaRepository<IncomeExpense, Long> {
}
