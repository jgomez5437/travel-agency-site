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
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    @JsonProperty("id")
    private Long id;

    @NotBlank
    @Column(name = "country")
    @JsonProperty("country_name")
    private String countryName;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @JsonProperty("create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date lastUpdate;

    @OneToMany(mappedBy = "country")
    @JsonProperty("divisions")
    private Set<Division> divisions = new HashSet<>();
}