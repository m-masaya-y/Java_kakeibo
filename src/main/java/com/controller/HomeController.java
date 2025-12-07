package com.example.kakeibo.controller;

import com.example.kakeibo.model.IncomeExpense;
import com.example.kakeibo.repository.IncomeExpenseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final IncomeExpenseRepository repository;

    public HomeController(IncomeExpenseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(Model model) {

        // DBから全部取得
        model.addAttribute("entries", repository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String addEntry(
            @RequestParam String month,
            @RequestParam int income,
            @RequestParam int expense
    ) {
        // DBに保存する
        IncomeExpense ie = new IncomeExpense();
        ie.setMonth(month);
        ie.setIncome(income);
        ie.setExpense(expense);

        repository.save(ie);

        return "redirect:/";
    }
}
