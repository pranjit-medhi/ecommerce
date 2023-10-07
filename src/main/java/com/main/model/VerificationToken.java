package com.main.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "verification_token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long tokenId;
    @Lob
    @Column(name = "token", nullable = false, unique = true)
    private String token;
    @Column(name = "created_at", nullable = false)
    private Timestamp createdTimeStamp;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
