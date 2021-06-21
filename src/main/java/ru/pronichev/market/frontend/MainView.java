package ru.pronichev.market.frontend;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import ru.pronichev.market.entities.Product;
import ru.pronichev.market.entities.User;
import ru.pronichev.market.repositories.ProductRepository;
import ru.pronichev.market.repositories.UserRepository;
import ru.pronichev.market.services.CartService;

@Route("main")
public class MainView extends VerticalLayout {
    private final Grid<Product> grid = new Grid<>(Product.class);

    private final ProductRepository productRepository;
    private final CartService cartService;
    private final UserRepository userRepository;

    private final User user;

    public MainView(ProductRepository productRepository,
                    CartService cartService,
                    UserRepository userRepository) {
        this.productRepository = productRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;

        this.user = userRepository.findOneByNameLike("test").get();

        initPage();
    }

    private void initPage() {
        initProductGrid();

        add(grid, initCartButton());
    }

    private HorizontalLayout initCartButton() {

        var addToCartButton = new Button("Добавить в корзину", items -> {
            grid.getSelectedItems().forEach(
                    product -> cartService.addProduct(user, product)
            );
            Notification.show("Товар успешно добавлен в корзину");
        });

        var toCartButton = new Button("Корзина", item -> {
            UI.getCurrent().navigate("cart");
        });

        return new HorizontalLayout(addToCartButton, toCartButton);
    }

    private void initProductGrid() {
        var products = productRepository.findAll();

        grid.setItems(products);
        grid.setColumns("name", "count");
        grid.setSizeUndefined();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        ListDataProvider<Product> dataProvider = DataProvider.ofCollection(products);
        grid.setDataProvider(dataProvider);

        grid.addColumn(new ComponentRenderer<>(item -> new HorizontalLayout()));
    }
}
