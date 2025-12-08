package com.example.kakeibo.model;

import jakarta.persistence.*;

@Entity
public class IncomeExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int income;

    @Column(nullable = false)
    private int expense;

    @Column(name = "month_value")  // month は予約語なので変更
    private String month;

    public IncomeExpense() {}

    public IncomeExpense(String month, int income, int expense) {
        this.month = month;
        this.income = income;
        this.expense = expense;
    }

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getIncome() { return income; }
    public void setIncome(int income) { this.income = income; }
    public int getExpense() { return expense; }
    public void setExpense(int expense) { this.expense = expense; }
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
}
