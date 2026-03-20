package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    @JsonProperty("id")
    private Long id;

    @NotBlank
    @Column(name = "customer_first_name")
    @JsonProperty("firstName")
    private String firstName;

    @NotBlank
    @Column(name = "customer_last_name")
    @JsonProperty("lastName")
    private String lastName;

    @NotBlank
    @Column(name = "address")
    @JsonProperty("address")
    private String address;

    @NotBlank
    @Column(name = "postal_code")
    @JsonProperty("postal_code")
    private String postalCode;

    @NotBlank
    @Column(name = "phone")
    @JsonProperty("phone")
    private String phone;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @JsonProperty("create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date lastUpdate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonProperty("carts")
    private Set<Cart> carts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "division_id")
    @JsonProperty("division")
    private Division division;

    public void addCart(Cart cart) {
        if (cart != null) {
            if (this.carts == null) {
                this.carts = new HashSet<>();
            }
            this.carts.add(cart);
            cart.setCustomer(this);
        }
    }
}
