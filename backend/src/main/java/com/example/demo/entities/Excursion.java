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

@Entity
@Table(name = "excursions")
@Getter
@Setter
@NoArgsConstructor
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "excursion_id")
    @JsonProperty("id")
    private Long id;

    @NotBlank
    @Column(name = "excursion_title")
    @JsonProperty("excursion_title")
    private String excursionTitle;

    @Positive
    @NotNull
    @JsonProperty("excursion_price")
    @Column(name = "excursion_price")
    private BigDecimal excursionPrice;

    @Column(name = "image_url")
    @JsonProperty("image_URL")
    private String imageURL;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "vacation_id", nullable = false)
    @JsonProperty("vacation")
    private Vacation vacation;

    @ManyToMany(mappedBy = "excursions")
    private Set<CartItem> cartItems = new HashSet<>();
}