package com.poly.lab5.controller;

import com.poly.lab5.service.ProductService;
import com.poly.lab5.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("total", cartService.getAmount());
        model.addAttribute("count", cartService.getCount());
        return "cart/index";
    }

    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id) {
        cartService.add(productService.findById(id));
        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        cartService.remove(id);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clear() {
        cartService.clear();
        return "redirect:/cart";
    }
}

