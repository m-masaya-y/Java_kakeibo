package com.example.kakeibo.controller;

import com.example.kakeibo.model.IncomeExpense;
import com.example.kakeibo.repository.IncomeExpenseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final IncomeExpenseRepository repo;

    public HomeController(IncomeExpenseRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("entries", repo.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String addEntry(
            @RequestParam String month,
            @RequestParam int income,
            @RequestParam int expense
    ) {
        IncomeExpense ie = new IncomeExpense();
        ie.setMonth(month);
        ie.setIncome(income);
        ie.setExpense(expense);

        repo.save(ie);
        return "redirect:/";
    }
}
