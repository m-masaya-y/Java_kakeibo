package com.example.kakeibo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

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
            @RequestParam("expense") int expense,
            Model model) {

        // ★ 正しく動くコンストラクタ
        IncomeExpense data = new IncomeExpense(month, income, expense);

        // ★ DBへ保存
        repository.save(data);

        // ★ 画面へ返却値必要なら
        model.addAttribute("message", "データを保存しました");

        return "index"; // ← ここはあなたのテンプレート名に変更してOK
    }
}
