package com.example.demo.services;

import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class Purchase {
    @Valid
    private Customer customer;

    @Valid
    private Cart cart;

    @Valid
    private Set<CartItem> cartItems;
}