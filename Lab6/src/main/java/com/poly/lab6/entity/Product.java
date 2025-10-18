package com.poly.lab6.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
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
    @Temporal(TemporalType.DATE)
    @Column(name = "CreateDate")
    private Date createDate = new Date();
    @Column(name = "Available")
    private Boolean available;
    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;
}
