package com.example.kakeibo.repository;

import com.example.kakeibo.model.IncomeExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IncomeExpenseRepository extends JpaRepository<IncomeExpense, Long> {

    @Query("SELECT i FROM IncomeExpense i WHERE i.month = :month")
    List<IncomeExpense> findByMonth(@Param("month") String month);
}
