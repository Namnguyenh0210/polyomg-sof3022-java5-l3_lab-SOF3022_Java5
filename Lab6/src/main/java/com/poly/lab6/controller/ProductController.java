package com.poly.lab6.controller;

import com.poly.lab6.dao.ProductDAO;
import com.poly.lab6.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductDAO dao;

    @RequestMapping("/product/sort")
    public String sort(Model model, @RequestParam("field") Optional<String> field) {
        Sort sort = Sort.by(Direction.DESC, field.orElse("price"));
        model.addAttribute("field", field.orElse("price").toUpperCase());
        List<Product> items = dao.findAll(sort);
        model.addAttribute("items", items);
        return "product/sort";
    }

    @RequestMapping("/product/page")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAll(pageable);
        model.addAttribute("page", page);
        return "product/page";
    }

    @RequestMapping("/product/sortpage")
    public String sortPage(Model model, @RequestParam("field") Optional<String> field, @RequestParam("p") Optional<Integer> p) {
        Sort sort = Sort.by(Direction.DESC, field.orElse("price"));
        Pageable pageable = PageRequest.of(p.orElse(0), 5, sort);
        Page<Product> page = dao.findAll(pageable);
        model.addAttribute("field", field.orElse("price").toUpperCase());
        model.addAttribute("page", page);
        return "product/sortpage";
    }
}

