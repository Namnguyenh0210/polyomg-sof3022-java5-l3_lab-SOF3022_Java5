package com.poly.lab6.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
    @Id
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "Fullname")
    private String fullname;
    @Column(name = "Email")
    private String email;
    @Column(name = "Photo")
    private String photo;
    @Column(name = "Activated")
    private boolean activated;
    @Column(name = "Admin")
    private boolean admin;
    @OneToMany(mappedBy = "account")
    private List<Order> orders;
}

