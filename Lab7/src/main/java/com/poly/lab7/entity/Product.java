package com.poly.lab7.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Image")
    private String image;

    @Column(name = "Price")
    private Double price;

    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
    private Date createDate = new Date();

    @Column(name = "Available")
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;
}

