package ru.pronichev.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", length = 36, nullable = false)
    private String name;

    @Column(name = "count")
    private Integer count;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Review> reviewList;
}