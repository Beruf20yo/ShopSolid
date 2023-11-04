package ru.example;

import lombok.Getter;
import lombok.Setter;
import ru.example.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Customer {
    @Getter
    private final String name;
    @Getter
    private byte[] password;
    @Getter
    @Setter
    private String address;
    @Getter
    private final List<Order> orders = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }
    public Customer(String name, String password) {
        this.name = name;
        this.password = password.getBytes();
    }

    public Customer(String name, String password, String address) {
        this.name = name;
        this.password = password.getBytes();
        this.address = address;
    }

    public void createOrder(Order order) {
        orders.add(order);
    }
    private boolean checkPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        return Arrays.equals(this.password, password.getBytes());
    }
    public String getAllCustomerInfo() {
        if(checkPassword()){
            StringBuilder sb = new StringBuilder();
            sb.append("Имя пользователя: ").append(name)
                    .append("\nПароль: ").append(new String(password))
                    .append("\nЗаказы за всё время: \n");
            for(var order: orders){
                sb.append(order.toString());
            }
            return sb.toString();
        } else {
            return "Неверный пароль";
        }
    }

}
