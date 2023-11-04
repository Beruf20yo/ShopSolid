package ru.example.customer;

import lombok.Getter;
import ru.example.order.Order;

import java.util.ArrayList;
import java.util.List;


public class Customer {
    @Getter
    private final String name;
    @Getter
    private final List<Order> offlineOrders = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void createOrder(Order order) {
        offlineOrders.add(order);
    }
    public String getAllCustomerInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Имя пользователя: ").append(name)
                .append("\nЗаказы за всё время: \n");
        for(var order: offlineOrders){
            sb.append(order.toString());
        }
        return sb.toString();
    }

}
