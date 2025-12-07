package com.example.kakeibo.model;

public class KakeiboEntry {
    private String month;
    private int income;
    private int expense;

    public KakeiboEntry(String month, int income, int expense) {
        this.month = month;
        this.income = income;
        this.expense = expense;
    }

    public String getMonth() {
        return month;
    }

    public int getIncome() {
        return income;
    }

    public int getExpense() {
        return expense;
    }

    public int getBalance() {
        return income - expense;
    }
}
