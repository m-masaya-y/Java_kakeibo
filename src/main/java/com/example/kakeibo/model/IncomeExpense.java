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

    public Long getId() {
        return id;
    }

    public int getIncome() {
        return income;
    }

    public int getExpense() {
        return expense;
    }

    public int getMonth() {
        return month;
    }
}
