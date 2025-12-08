package com.example.kakeibo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.kakeibo.model.IncomeExpense;
import com.example.kakeibo.repository.IncomeExpenseRepository;

@Controller
public class HomeController {

    @Autowired
    private IncomeExpenseRepository repository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("dataList", repository.findAll());
        return "index";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam String month,
            @RequestParam int income,
            @RequestParam int expense) {

        repository.save(new IncomeExpense(month, income, expense));
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
