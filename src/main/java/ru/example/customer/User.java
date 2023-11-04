package ru.example.customer;

import lombok.Getter;
import lombok.Setter;
import ru.example.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User extends Customer{
    @Getter
    private byte[] password;
    @Getter
    @Setter
    private String address;
    @Getter
    private final List<Order> onlineOrders = new ArrayList<>();

    public User(String name, String password) {
        super(name);
        this.password = password.getBytes();
    }

    public User(String name, String password, String address) {
        super(name);
        this.password = password.getBytes();
        this.address = address;
    }

    public User(String name) {
        super(name);
    }
    @Override
    public void createOrder(Order order) {
        onlineOrders.add(order);
    }
    @Override
    public String getAllCustomerInfo() {
        if(checkPassword()){
            StringBuilder sb = new StringBuilder();
            sb.append("Имя пользователя: ").append(super.getName())
                    .append("\nПароль: ").append(new String(password))
                    .append("\nЗаказы за всё время: \n");
            for(var order: onlineOrders){
                sb.append(order.toString());
            }
            return sb.toString();
        } else {
            return "Неверный пароль";
        }
    }
    private boolean checkPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        return Arrays.equals(this.password, password.getBytes());
    }
}
