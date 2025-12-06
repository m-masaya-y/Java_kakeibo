package com.example.kakeibo;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class KakeiboController {

    private List<Integer> incomes = new ArrayList<>();
    private List<Integer> expenses = new ArrayList<>();

    @GetMapping("/")
    public String home() {
        return "Kakeibo App is running!";
    }

    @GetMapping("/add")
    public String add(@RequestParam String type, @RequestParam int amount) {
        if (type.equals("income")) {
            incomes.add(amount);
            return "Income added: " + amount;
        } else if (type.equals("expense")) {
            expenses.add(amount);
            return "Expense added: " + amount;
        } else {
            return "Invalid type";
        }
    }

    @GetMapping("/sum")
    public String sum() {
        int totalIncome = incomes.stream().mapToInt(Integer::intValue).sum();
        int totalExpense = expenses.stream().mapToInt(Integer::intValue).sum();
        int result = totalIncome - totalExpense;

        return "今月の収支合計: " + result + "円";
    }
}
