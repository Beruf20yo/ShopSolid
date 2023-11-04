package ru.example.order;

import lombok.Getter;
import ru.example.product.Product;

import java.util.List;
public class Order {
    @Getter
    private int orderId = 0;
    @Getter
    private final String orderDate;
    private final List<Product> products;
    private final int orderPrice;
    private final String storeName;

    public Order(String orderDate, List<Product> products, int orderPrice, String storeName) {
        orderId ++;
        this.orderDate = orderDate;
        this.products = products;
        this.orderPrice = orderPrice;
        this.storeName = storeName;
    }

    private String allProductNames() {
        StringBuilder sb = new StringBuilder();
        for (var prod : products) {
            sb.append("    ").append(prod.toString()).append("\n");
        }
        return sb.toString();
    }
    @Override
    public String toString() {
        return "Магазин "+ storeName +"\n" +
                "Заказ № " + orderId + " на сумму "
                + orderPrice + ":\n"
                + allProductNames() + "\n" +
                "Заказ сделан : " +
                orderDate;
    }
}
