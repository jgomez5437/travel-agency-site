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
@Table(name = "vacations")
@Getter
@Setter
@NoArgsConstructor
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id")
    @JsonProperty("id")
    private Long id;

    @NotBlank
    @Column(name = "vacation_title")
    @JsonProperty("vacation_title")
    private String vacationTitle;

    @NotBlank
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Positive
    @NotNull
    @Column(name = "travel_fare_price")
    @JsonProperty("travel_price")
    private BigDecimal travelPrice;

    @Column(name = "image_url")
    @JsonProperty("image_URL")
    private String imageURL;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @JsonProperty("create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date lastUpdate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacation")
    @JsonProperty("excursions")
    private Set<Excursion> excursions = new HashSet<>();
}