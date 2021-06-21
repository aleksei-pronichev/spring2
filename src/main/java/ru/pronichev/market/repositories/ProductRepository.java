package ru.pronichev.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pronichev.market.entities.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}