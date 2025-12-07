package com.example.kakeibo.model;

import jakarta.persistence.*;

@Entity
public class IncomeExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String month;
    private int income;
    private int expense;

    public int getBalance() {
        return income - expense;
    }

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public int getIncome() { return income; }
    public void setIncome(int income) { this.income = income; }

    public int getExpense() { return expense; }
    public void setExpense(int expense) { this.expense = expense; }
}
