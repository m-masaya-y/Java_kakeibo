package com.example.kakeibo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class IncomeExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String monthName; // 月
    private int income;
    private int expense;

    public IncomeExpense() {}

    public IncomeExpense(String monthName, int income, int expense) {
        this.monthName = monthName;
        this.income = income;
        this.expense = expense;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMonthName() { return monthName; }
    public void setMonthName(String monthName) { this.monthName = monthName; }

    public int getIncome() { return income; }
    public void setIncome(int income) { this.income = income; }

    public int getExpense() { return expense; }
    public void setExpense(int expense) { this.expense = expense; }
}
