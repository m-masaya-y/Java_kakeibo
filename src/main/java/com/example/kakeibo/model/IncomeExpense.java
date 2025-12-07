package com.example.kakeibo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "income_expense")
public class IncomeExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "month_value") // ← 予約語回避
    private String month;

    private int income;
    private int expense;

    public IncomeExpense() {}

    public IncomeExpense(String month, int income, int expense) {
        this.month = month;
        this.income = income;
        this.expense = expense;
    }

    public Long getId() {
        return id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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
}
