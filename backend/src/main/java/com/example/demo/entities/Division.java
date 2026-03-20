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
@Table(name = "divisions")
@Getter
@Setter
@NoArgsConstructor
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id")
    @JsonProperty("id")
    private Long id;

    @NotBlank
    @Column(name = "division")
    @JsonProperty("division_name")
    private String divisionName;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @JsonProperty("create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date lastUpdate;

    @Column(name = "country_id")
    @JsonProperty("country_id")
    private Long country_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false, insertable = false, updatable = false)
    @JsonProperty("country")
    private Country country;

    @OneToMany(mappedBy = "division")
    @JsonProperty("customers")
    private Set<Customer> customers = new HashSet<>();

    public void addCustomer(Customer customer) {
        if (customer != null) {
            customers.add(customer);
            customer.setDivision(this);
        }
    }
}