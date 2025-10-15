package com.poly.lab5.service;

import com.poly.lab5.model.CartItem;
import com.poly.lab5.model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartService {
    private final Map<Long, CartItem> cart = new HashMap<>();

    public void add(Product product) {
        cart.compute(product.getId(), (id, item) -> {
            if (item == null) return new CartItem(product, 1);
            item.setQuantity(item.getQuantity() + 1);
            return item;
        });
    }

    public void remove(Long productId) {
        cart.remove(productId);
    }

    public void clear() {
        cart.clear();
    }

    public Collection<CartItem> getItems() {
        return cart.values();
    }

    public int getCount() {
        return cart.values().stream().mapToInt(CartItem::getQuantity).sum();
    }

    public double getAmount() {
        return cart.values().stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
    }
}
