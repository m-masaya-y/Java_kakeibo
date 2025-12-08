package com.example.kakeibo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.example.kakeibo.model.IncomeExpense;
import com.example.kakeibo.repository.IncomeExpenseRepository;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private IncomeExpenseRepository repository;

    @GetMapping("/")
    public String index(Model model) {
        List<IncomeExpense> data = repository.findAll();
        model.addAttribute("dataList", data);
        return "index";
    }

    @PostMapping("/save")
    public String saveData(
            @RequestParam("month") String month,
            @RequestParam("income") int income,
            @RequestParam("expense") int expense) {

        IncomeExpense data = new IncomeExpense(month, income, expense);
        repository.save(data);
        return "redirect:/"; // 保存後に一覧表示
    }

    @PostMapping("/delete")
    public String deleteData(@RequestParam("id") Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
