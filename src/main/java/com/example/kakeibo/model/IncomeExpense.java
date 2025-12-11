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

    // ★ 修正：String に変更
    private String month;

    public IncomeExpense() {}

    // ★ 修正：month を String で受け取る
    public IncomeExpense(int income, int expense, String month) {
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

    // String month 版 Getter / Setter
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
