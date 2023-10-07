package com.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address_details")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Long addressId;
    @Column(name = "address_line_1", nullable = false, length = 512)
    private String addressLine1;
    @Column(name = "address_line_2", length = 512)
    private String addressLine2;
    @Column(name = "city", nullable = false, length = 75)
    private String city;
    @Column(name = "country", nullable = false, length = 75)
    private String country;
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name ="customer_id", nullable = false)
    private Customer customer;



}
