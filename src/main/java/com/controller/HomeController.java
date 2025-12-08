package com.example.kakeibo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kakeibo.model.IncomeExpense;
import com.example.kakeibo.repository.IncomeExpenseRepository;

@Controller
public class HomeController {

    @Autowired
    private IncomeExpenseRepository repository;

    @GetMapping("/")
    public String index(Model model) {
        List<IncomeExpense> dataList = repository.findAll();
        // 月順にソート
        dataList.sort((a, b) -> Integer.compare(
            Integer.parseInt(a.getMonth()),
            Integer.parseInt(b.getMonth())
        ));
        model.addAttribute("dataList", dataList);
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
        return "redirect:/"; // 削除後に一覧表示
    }
}
