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

        // 月順にソート
        list.sort(Comparator.comparingInt(IncomeExpense::getMonthValue));
        model.addAttribute("dataList", list);

        // ★ 月別合計（収入・支出）
        Map<Integer, int[]> monthlySummary = new LinkedHashMap<>();

        for (IncomeExpense item : list) {
            monthlySummary.putIfAbsent(
                item.getMonthValue(),
                new int[]{0, 0} // [収入合計, 支出合計]
            );
            monthlySummary.get(item.getMonthValue())[0] += item.getIncome();
            monthlySummary.get(item.getMonthValue())[1] += item.getExpense();
        }

        model.addAttribute("monthlySummary", monthlySummary);

        return "index";
    }

    @PostMapping("/add")
    public String add(@RequestParam int income,
                      @RequestParam int expense,
                      @RequestParam int month) {

        IncomeExpense entry = new IncomeExpense(income, expense, month);
        repository.save(entry);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
