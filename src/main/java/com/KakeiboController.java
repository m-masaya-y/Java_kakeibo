package com.example.kakeibo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KakeiboController {

    @GetMapping("/")
    public String index(Model model) {
        // サンプルの今月の収支データ
        int income = 300000;
        int expense = 120000;
        int balance = income - expense;

        model.addAttribute("income", income);
        model.addAttribute("expense", expense);
        model.addAttribute("balance", balance);

        return "index";  // src/main/resources/templates/index.html を表示
    }
}
