package com.poly.lab7.controller;

import com.poly.lab7.dao.ProductDAO;
import com.poly.lab7.entity.Product;
import com.poly.lab7.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductDAO dao;

    @Autowired
    private SessionService session;

    // Bài 1: Tìm kiếm theo khoảng giá sử dụng @Query
    @RequestMapping("/product/search")
    public String search(Model model,
                        @RequestParam("min") Optional<Double> min,
                        @RequestParam("max") Optional<Double> max) {
        double minPrice = min.orElse(Double.MIN_VALUE);
//        double minPrice = min.orElse(0.0); // mặc định 0 nếu không nhập
        double maxPrice = max.orElse(Double.MAX_VALUE);
        List<Product> items = dao.findByPrice(minPrice, maxPrice);
        model.addAttribute("items", items);
        return "product/search";
    }

    // Bài 2: Tìm kiếm theo từ khóa với phân trang sử dụng @Query
    @RequestMapping("/product/search-and-page")
    public String searchAndPage(Model model,
                               @RequestParam("keywords") Optional<String> kw,
                               @RequestParam("p") Optional<Integer> p) {
        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findByKeywords("%" + kwords + "%", pageable);
        model.addAttribute("page", page);
        return "product/search-and-page";
    }

    // Bài 4: Tìm kiếm theo khoảng giá sử dụng DSL
    @RequestMapping("/product/search-dsl")
    public String searchDsl(Model model,
                           @RequestParam("min") Optional<Double> min,
                           @RequestParam("max") Optional<Double> max) {
        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);
        List<Product> items = dao.findByPriceBetween(minPrice, maxPrice);
        model.addAttribute("items", items);
        return "product/search-dsl";
    }

    // Bài 5: Tìm kiếm theo từ khóa với phân trang sử dụng DSL
    @RequestMapping("/product/search-and-page-dsl")
    public String searchAndPageDsl(Model model,
                                  @RequestParam("keywords") Optional<String> kw,
                                  @RequestParam("p") Optional<Integer> p) {
        String kwords = kw.orElse(session.get("keywords_dsl", ""));
        session.set("keywords_dsl", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAllByNameLike("%" + kwords + "%", pageable);
        model.addAttribute("page", page);
        return "product/search-and-page-dsl";
    }
}

