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

    // ★ 予約語回避：month → monthName
    @Column(name = "month_name")
    private String monthName;

    public IncomeExpense() {}

    public IncomeExpense(int income, int expense, String monthName) {
        this.income = income;
        this.expense = expense;
        this.monthName = monthName;
    }

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

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }
}
