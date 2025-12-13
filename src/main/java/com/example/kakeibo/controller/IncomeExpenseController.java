package com.example.kakeibo.controller;

import com.example.kakeibo.model.IncomeExpense;
import com.example.kakeibo.repository.IncomeExpenseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class IncomeExpenseController {

    private final IncomeExpenseRepository repository;

    public IncomeExpenseController(IncomeExpenseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<IncomeExpense> list = repository.findAll();

        // 月でソート
        list.sort(Comparator.comparingInt(IncomeExpense::getMonth));

        model.addAttribute("dataList", list);
        return "index";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam int income,
            @RequestParam int expense,
            @RequestParam int month
    ) {
        repository.save(new IncomeExpense(income, expense, month));
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
