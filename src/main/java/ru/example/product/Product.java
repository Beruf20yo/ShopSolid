package ru.example.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {
    private String productName;
    private int price;
    private ProductType type;

    @Override
    public String toString() {
        return productName + ":\n" +
                "  цена - " + price + "\n" +
                "  тип - " + type.getDescription();
    }
}
