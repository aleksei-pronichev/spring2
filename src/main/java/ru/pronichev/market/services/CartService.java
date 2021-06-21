package ru.pronichev.market.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pronichev.market.dto.ProductCartDto;
import ru.pronichev.market.entities.Cart;
import ru.pronichev.market.entities.Product;
import ru.pronichev.market.entities.User;
import ru.pronichev.market.evceptions.NotFoundException;
import ru.pronichev.market.repositories.CartRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void addProduct(User user, Product product) {
        var cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setCount(1);

        cartRepository.save(cart);
    }

    public void deleteProduct(User user, Product product) {
        var cart = cartRepository.getByUserAndProduct(user, product)
                .orElseThrow(NotFoundException::new);
        cartRepository.delete(cart);
    }

    public void increaseProductCount(ProductCartDto productCartDto) {
        var cart = cartRepository.getById(productCartDto.getId());
        cart.increment();
        cartRepository.save(cart);
    }

    public void decreaseProductCount(ProductCartDto productCartDto) {
        var cart = cartRepository.getById(productCartDto.getId());
        cart.decrement();
        cartRepository.save(cart);
    }

    public List<ProductCartDto> getUserProducts(User user) {
        return cartRepository.findAllByUser(user).stream()
                .map(ProductCartDto::new)
                .collect(Collectors.toList());
    }

}