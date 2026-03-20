package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "carts")
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "order_tracking_number")
    @JsonProperty("orderTrackingNumber")
    private String orderTrackingNumber;

    @Column(name = "package_price")
    @JsonProperty("package_price")
    private BigDecimal packagePrice;

    @Positive
    @NotNull
    @Column(name = "party_size")
    @JsonProperty("party_size")
    private int partySize;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @JsonProperty("create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    @JsonProperty("cartItems")
    private Set<CartItem> cartItem = new HashSet<>();
    public void addCartItem(CartItem item) {
        if (item != null) {
            cartItem.add(item);
            item.setCart(this);
        }

    }
}