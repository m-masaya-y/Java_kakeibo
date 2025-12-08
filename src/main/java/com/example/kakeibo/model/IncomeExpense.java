package com.example.kakeibo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class IncomeExpense {

```
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private int income;
private int expense;

@Column(name = "month_value")  // H2 の予約語回避
private String month;

public IncomeExpense() {}

public IncomeExpense(String month, int income, int expense) {
    this.month = month;
    this.income = income;
    this.expense = expense;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
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

public String getMonth() {
    return month;
}

public void setMonth(String month) {
    this.month = month;
}
```

}
