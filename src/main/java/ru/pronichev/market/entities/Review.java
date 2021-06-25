package ru.pronichev.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private UUID id;

    @Column(name = "text", length = Integer.MAX_VALUE)
    private String text;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}