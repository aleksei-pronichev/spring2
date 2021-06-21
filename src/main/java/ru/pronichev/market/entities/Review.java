package ru.pronichev.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "text", length = Integer.MAX_VALUE)
    private String text;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}