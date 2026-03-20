package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    @JsonProperty("id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonProperty("cart")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "vacation_id", nullable = false)
    @JsonProperty("vacation")
    private Vacation vacation;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @JsonProperty("create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date lastUpdate;

    @ManyToMany
    @JoinTable(
            name = "excursion_cartitem",
            joinColumns = @JoinColumn(name = "cart_item_id"),
            inverseJoinColumns = @JoinColumn(name = "excursion_id")
    )
    @JsonProperty("excursions")
    private Set<Excursion> excursions = new HashSet<>();
}
