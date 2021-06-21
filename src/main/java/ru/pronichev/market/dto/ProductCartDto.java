package ru.pronichev.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pronichev.market.entities.Cart;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductCartDto {
    private UUID id;
    private String name;
    private int count;

    public ProductCartDto(Cart cart) {
        this.id = cart.getId();
        this.name = cart.getProduct().getName();
        this.count = cart.getCount();
    }
}
