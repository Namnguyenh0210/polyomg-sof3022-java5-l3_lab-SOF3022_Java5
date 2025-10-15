package com.poly.lab5.controller;

import com.poly.lab5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping({"/products", "/"})
    public String list(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size) {
        int totalProducts = productService.getTotalProducts();
        int totalPages = productService.getTotalPages(size);
        int startPage = Math.max(0, page - 2);
        int endPage = Math.min(totalPages - 1, page + 2);
        model.addAttribute("products", productService.findPage(page, size));
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "product/list";
    }
}

