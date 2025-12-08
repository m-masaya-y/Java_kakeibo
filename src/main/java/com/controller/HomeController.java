package com.example.kakeibo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.kakeibo.model.IncomeExpense;
import com.example.kakeibo.repository.IncomeExpenseRepository;

@Controller
public class HomeController {

    @Autowired
    private IncomeExpenseRepository repository;

    @PostMapping("/save")
    public String saveData(
            @RequestParam("month") String month,
            @RequestParam("income") int income,
            @RequestParam("expense") int expense) {

        IncomeExpense data = new IncomeExpense(month, income, expense);
        repository.save(data);

        return "redirect:/"; // 保存後トップにリダイレクト
    }
}
