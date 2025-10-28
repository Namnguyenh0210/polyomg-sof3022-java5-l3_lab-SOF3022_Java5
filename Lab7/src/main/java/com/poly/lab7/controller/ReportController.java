package com.poly.lab7.controller;

import com.poly.lab7.dao.ProductDAO;
import com.poly.lab7.dto.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ProductDAO dao;

    // Bài 3: Tổng hợp dữ liệu tồn kho theo danh mục
    @RequestMapping("/report/inventory-by-category")
    public String inventory(Model model) {
        List<Report> items = dao.getInventoryByCategory();
        model.addAttribute("items", items);
        return "report/inventory-by-category";
    }
}

