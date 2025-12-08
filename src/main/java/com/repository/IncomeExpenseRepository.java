package com.example.kakeibo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kakeibo.model.IncomeExpense;

public interface IncomeExpenseRepository extends JpaRepository<IncomeExpense, Long> {
}
