package com.example.kakeibo.repository;

import com.example.kakeibo.model.IncomeExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeExpenseRepository extends JpaRepository<IncomeExpense, Long> {
}
