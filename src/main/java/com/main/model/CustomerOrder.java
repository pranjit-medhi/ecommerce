package com.main.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_orders")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Customer customer;
    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderQuantites> quantities = new ArrayList<>();

}
