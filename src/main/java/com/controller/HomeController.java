package com.controller;

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

    // 一覧表示
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("dataList", repository.findAll());
        return "index";
    }

    // データ追加
    @PostMapping("/add")
    public String add(@RequestParam String income,
                      @RequestParam String expense,
                      @RequestParam String month) {

        int inc = Integer.parseInt(income);
        int exp = Integer.parseInt(expense);
        int mon = Integer.parseInt(month);

        IncomeExpense entry = new IncomeExpense(inc, exp, mon);
        repository.save(entry);
        return "redirect:/";
    }

    // データ追加
@PostMapping("/add")
public String add(@RequestParam String income,
                  @RequestParam String expense,
                  @RequestParam String month) {

    int inc = Integer.parseInt(income);
    int exp = Integer.parseInt(expense);

    // ★ 修正：month は文字列のまま保存
    IncomeExpense entry = new IncomeExpense(inc, exp, month);

    repository.save(entry);
    return "redirect:/";
    }
}
