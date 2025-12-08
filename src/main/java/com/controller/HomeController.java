package com.example.kakeibo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return "index"; // Thymeleafテンプレート
    }

    @PostMapping("/save")
    public String saveData(@RequestParam String month,
                           @RequestParam String type,
                           @RequestParam int amount,
                           Model model) {

        IncomeExpense data = new IncomeExpense(month, type.equals("income") ? amount : 0,
                                               type.equals("expense") ? amount : 0);
        repository.save(data);
        return "redirect:/"; // 保存後トップに戻る
    }

    @PostMapping("/delete")
    public String deleteData(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
