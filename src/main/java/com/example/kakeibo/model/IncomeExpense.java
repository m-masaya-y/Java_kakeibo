package com.example.kakeibo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "income_expense")
public class IncomeExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int income;
    private int expense;
    private int month;

    public IncomeExpense() {}

    public IncomeExpense(int income, int expense, int month) {
        this.income = income;
        this.expense = expense;
        this.month = month;
    }

    // --- Getter / Setter ---
    public Long getId() {
        return id;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
