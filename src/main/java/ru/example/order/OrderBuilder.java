package ru.example.order;

import ru.example.product.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderBuilder implements OrderBuilderI{
    private final String storeName;
    private final List<Product> products = new ArrayList<>();
    private int orderPrice = 0;
    public OrderBuilder(String storeName) {
        this.storeName = storeName;
    }


    public OrderBuilder addProduct(Product product) {
        orderPrice += product.getPrice();
        products.add(product);
        return this;
    }

    @Override
    public Order build() {
        return new Order(LocalDateTime.now().toString(), products, orderPrice, storeName);
    }
}
