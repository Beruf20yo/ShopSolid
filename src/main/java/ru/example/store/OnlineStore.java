package ru.example.store;


import ru.example.customer.Customer;
import ru.example.customer.User;
import ru.example.order.Order;
import ru.example.product.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OnlineStore extends Store {
    private final List<User> customers = new ArrayList<>();

    public OnlineStore(String name, List<Product> products) {
        super(name, products);
    }

    public User registerUser() {
        User customer;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя: ");
        String name = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();
        System.out.println("""
                Хотите указать адрес доставки?
                1. Да
                2. Нет""");
        String chose = scanner.nextLine();
        if(Integer.parseInt(chose) == 2){
            customer = new User(name, password);
        } else {
            System.out.println("Введите адрес :");
            customer = new User(name, password, scanner.nextLine());
        }
        customers.add(customer);
        return customer;

    }
    public void makeOrderToHome(User user, Order order) {
        String address = user.getAddress();
        if(!(address.isEmpty())) {
            System.out.println("Адрес для доставки" + address);
        } else {
            System.out.println("Вам нужно указать адрес доставки\n" +
                    "Введите адресс: ");
            Scanner scanner = new Scanner(System.in);
            address = scanner.nextLine();
        }
        System.out.println("Ваш заказ "+ order.getOrderId() +" будет доставлен по адрес" +address);
    }
    public User findCustomer(String name, String password) {
        List<User> all = customers.stream()
               .filter(x -> x.getName().equals(name))
                .filter(x-> Arrays.equals(x.getPassword(), password.getBytes()))
                .toList();
        if(all.isEmpty()){
            System.out.println("У вас нет аккаунта. Давайте зарегистрируемся");
            return registerUser();
        }
        return all.get(0);
    }

}
