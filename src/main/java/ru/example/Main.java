package ru.example;

import ru.example.product.Product;
import ru.example.product.ProductType;
import ru.example.store.OnlineStore;
import ru.example.store.Store;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Store store = new Store("<Шестёрочка>", productsForStore());
        OnlineStore onlineStore = new OnlineStore("<Яникс Маркет>", productsForOnlineStore());
        ConsoleApp consoleApp = new ConsoleApp(store, onlineStore);
        consoleApp.startDay();
    }
    private static List<Product> productsForOnlineStore() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Кефир", 60, ProductType.LACTIC));
        products.add(new Product("Лосось", 500, ProductType.FISH));
        products.add(new Product("Lay's", 110, ProductType.SNACKS));
        products.add(new Product("Апельсины", 240, ProductType.FRUITS));
        return products;
    }
    private static List<Product> productsForStore() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Бекон", 423, ProductType.MEAT));
        products.add(new Product("Твикс", 45, ProductType.CANDIES));
        products.add(new Product("Капуста", 149, ProductType.VEGETABLES));
        products.add(new Product("Гречка", 67, ProductType.GREATS));
        return products;
    }
}