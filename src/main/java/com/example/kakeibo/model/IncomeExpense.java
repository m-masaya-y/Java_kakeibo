package com.example.kakeibo.model;

import jakarta.persistence.*;

@Entity
public class IncomeExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "month_value")  // ← 予約語回避
    private String monthValue;

    private int income;
    private int expense;

    public IncomeExpense() {}

    public IncomeExpense(String month, int income, int expense) {
        this.monthValue = month;
        this.income = income;
        this.expense = expense;
    }

    public int getBalance() {
        return income - expense;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMonthValue() { return monthValue; }
    public void setMonthValue(String monthValue) { this.monthValue = monthValue; }

    public int getIncome() { return income; }
    public void setIncome(int income) { this.income = income; }

    public int getExpense() { return expense; }
    public void setExpense(int expense) { this.expense = expense; }
}
