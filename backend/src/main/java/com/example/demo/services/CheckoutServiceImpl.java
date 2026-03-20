package com.example.demo.services;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Cart cart = purchase.getCart();
        Set<CartItem> cartItems = purchase.getCartItems();
        Customer customer = purchase.getCustomer();

        if (cart != null) {
            cart.setId(null);

            String trackingNumber = UUID.randomUUID().toString();
            cart.setOrderTrackingNumber(trackingNumber);
            cart.setStatus(StatusType.ordered);

            for (CartItem item : cartItems) {
                item.setId(null);
                item.setCart(cart);
                cart.addCartItem(item);
            }

            customer.addCart(cart);

            customerRepository.save(customer);

            return new PurchaseResponse(trackingNumber);
        }
        return new PurchaseResponse("ERROR");
    }
}