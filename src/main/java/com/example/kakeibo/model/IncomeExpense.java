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

    // ★ month は予約語なので変更
    @Column(name = "month_value", nullable = false)
    private int monthValue;

    public IncomeExpense() {}

    public IncomeExpense(int income, int expense, int monthValue) {
        this.income = income;
        this.expense = expense;
        this.monthValue = monthValue;
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

    public int getMonthValue() {
        return monthValue;
    }
}
