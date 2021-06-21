package ru.pronichev.market.frontend;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import ru.pronichev.market.dto.ProductCartDto;
import ru.pronichev.market.entities.User;
import ru.pronichev.market.repositories.UserRepository;
import ru.pronichev.market.services.CartService;

@Route("cart")
public class CartView extends VerticalLayout {
    private final Grid<ProductCartDto> grid = new Grid<>(ProductCartDto.class);

    private final CartService cartService;

    private final UserRepository userRepository;

    private final User user;

    public CartView(CartService cartService, UserRepository userRepository) {
        this.cartService = cartService;
        this.userRepository = userRepository;

        this.user = userRepository.findOneByNameLike("test").get();

        initCartGrid();
        add(grid, initMainButton());
    }

    private HorizontalLayout initMainButton() {
        var toMainButton = new Button("На главную", item -> {
            UI.getCurrent().navigate("main");
        });

        return new HorizontalLayout(toMainButton);
    }

    private void initCartGrid() {
        var cartProducts = cartService.getUserProducts(user);

        grid.setItems(cartProducts);
        grid.setColumns("name", "count");
        grid.setSizeUndefined();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        ListDataProvider<ProductCartDto> dataProvider = DataProvider.ofCollection(cartProducts);
        grid.setDataProvider(dataProvider);

        grid.addColumn(new ComponentRenderer<>(item -> {
            var plusButton = new Button("+", i -> {
                cartService.increaseProductCount(item);
                grid.getDataProvider().refreshItem(item);
            });

            var minusButton = new Button("-", i -> {
                cartService.decreaseProductCount(item);
                grid.getDataProvider().refreshItem(item);
            });

            return new HorizontalLayout(plusButton, minusButton);
        }));
    }
}