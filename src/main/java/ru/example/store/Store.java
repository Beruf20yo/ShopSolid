package ru.example.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.example.product.Product;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
public class Store {
    @Getter
    private final String name;
    private List<Product> products;
    public List<String> AllProducts() {
        return products.stream().map(Product::getProductName)
                .toList();
    }

    public String FindProductByName(String name) {
        return products.stream()
                .filter(x -> x.getProductName().equalsIgnoreCase(name))
                .map(Product::toString).toList().get(0);
    }
    public Product getProduct(String name) {
        return products.stream()
                .filter(x-> x.getProductName().equalsIgnoreCase(name))
                .toList().get(0);
    }
    public List<String> getProductTypes() {
        return products.stream()
                .map(x->x.getType().getDescription())
                .toList();
    }
    public List<String> ProductsByType(String type) {
        return products.stream()
                .filter(x -> x.getType().getDescription().equalsIgnoreCase(type))
                .map(Product::getProductName).toList();
    }

}
