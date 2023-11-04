package ru.example.store;


import ru.example.Customer;
import ru.example.order.Order;
import ru.example.product.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OnlineStore extends Store {
    private final List<Customer> customers = new ArrayList<>();

    public OnlineStore(String name, List<Product> products) {
        super(name, products);
    }

    public Customer registerCustomer() {
        Customer customer;
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
            customer = new Customer(name, password);
        } else {
            System.out.println("Введите адрес :");
            customer = new Customer(name, password, scanner.nextLine());
        }
        customers.add(customer);
        return customer;

    }
    public void makeOrderToHome(Customer customer, Order order) {
        String address = customer.getAddress();
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
    public Customer findCustomer(String name, String password) {
        List<Customer> all = customers.stream()
               .filter(x -> x.getName().equals(name))
                .filter(x-> Arrays.equals(x.getPassword(), password.getBytes()))
                .toList();
        if(all.isEmpty()){
            System.out.println("У вас нет аккаунта. Давайте зарегистрируемся");
            return registerCustomer();
        }
        return all.get(0);
    }
    //


}
