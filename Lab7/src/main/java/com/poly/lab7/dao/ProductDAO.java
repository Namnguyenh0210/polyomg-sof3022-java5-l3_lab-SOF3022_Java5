package com.poly.lab7.dao;

import com.poly.lab7.dto.Report;
import com.poly.lab7.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {

    // Bài 1: Tìm kiếm theo khoảng giá sử dụng @Query
    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
    List<Product> findByPrice(double minPrice, double maxPrice);

    // Bài 2: Tìm kiếm theo từ khóa với phân trang sử dụng @Query
    @Query("FROM Product o WHERE o.name LIKE ?1")
    Page<Product> findByKeywords(String keywords, Pageable pageable);

    // Bài 3: Tổng hợp dữ liệu tồn kho theo danh mục
    @Query("SELECT o.category AS group, sum(o.price) AS sum, count(o) AS count "
            + " FROM Product o "
            + " GROUP BY o.category"
            + " ORDER BY sum(o.price) DESC")
    List<Report> getInventoryByCategory();

    // Bài 4: Tìm kiếm theo khoảng giá sử dụng DSL
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // Bài 5: Tìm kiếm theo từ khóa với phân trang sử dụng DSL
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);
}

