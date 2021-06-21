package ru.pronichev.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pronichev.market.entities.Cart;
import ru.pronichev.market.entities.Product;
import ru.pronichev.market.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> getByUserAndProduct(User user, Product product);

    List<Cart> findAllByUser(User user);
}