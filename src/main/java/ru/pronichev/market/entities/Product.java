package ru.pronichev.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 36, nullable = false)
    private String name;

    @Column(name = "count")
    private Integer count;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Review> reviewList;
}