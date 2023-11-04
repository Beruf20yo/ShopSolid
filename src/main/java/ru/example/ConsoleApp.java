package ru.example;

import ru.example.customer.Customer;
import ru.example.customer.User;
import ru.example.order.Order;
import ru.example.order.OrderBuilder;
import ru.example.store.OnlineStore;
import ru.example.store.Store;

import java.util.Scanner;

public class ConsoleApp {
    private final Scanner scanner = new Scanner(System.in);
    private Customer customer;
    private User user;
    private final Store store;
    private final OnlineStore onlineStore;
    private int day = 1;

    public ConsoleApp(Store store, OnlineStore onlineStore) {
        this.store = store;
        this.onlineStore = onlineStore;
    }
    //Magic
    private int checkChose(int max) {
        String input = scanner.nextLine();
        int chose = 0;
        while (chose < 1 || chose > max) {
            try {
                chose = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Нужно ввести цифру");
            }
        }
        return chose;
    }

    public void startDay() {
        System.out.println("<<Предистория>>\n" +
                "Вы проснулись утром " + day + " дня, хотите кушать, холодильник пуст.\n" +
                "Что будете делать?\n" +
                "1. Лягу дальше спать в надежде, что холодильник обновится\n" +
                "2. Раздобуду где-нибудь еды\n" +
                "3. Покинуть программу");
        int max = 3;
        switch (checkChose(max)) {
            case 1 -> {
                day++;
                startDay();
            }
            case 2 -> needSomeFood();
            case 3 -> System.out.println("Спасибо, что пользовались нашей программой!");
        }
    }
    private void needSomeFood() {
        System.out.println("""
                Как вы раздобудете еду?
                1. Схожу в магазин
                2. Закажу доставку
                """);
        int max = 2;
        switch (checkChose(max)){
            case 1 -> goToStore();
            case 2 -> checkCustomerAccount();
        }
    }
    private void goToStore() {
        System.out.println("Вы пришли в магазин " + store.getName()+"\n" +
                "Как к вам обращаться?");
        String name = scanner.nextLine();
        customer = new Customer(name);
        Order order = makeOrder(store);
        System.out.println("Вы забрали заказ -\n" + order.toString());
        customer.createOrder(order);
        System.out.println("""
                Вы снова дома, купили продукты из магазина
                Нужно ли вам ещё что-то?
                1. Да
                2. Нет""");
        int max = 2;
        switch (checkChose(max)) {
            case 1 -> needSomeFood();
            case 2 ->{
                day ++;
                startDay();
            }
        }
    }
    private void onlineMarketInterface(){
        System.out.println("Что вы собираетесь сделать в онлайн магазине " + onlineStore.getName() + "?\n" +
                "1. Сделать заказ\n" +
                "2. Узнать информацию об аккаунте\n" +
                "3. Покинуть онлайн магазин");
        switch (checkChose(3)){
            case 1 -> orderDelivery();
            case 2 -> {
                System.out.println(user.getAllCustomerInfo());
                onlineMarketInterface();
            }
            case 3 -> needSomeFood();

        }
    }
    private void orderDelivery() {
        Order order = makeOrder(onlineStore);
        customer.createOrder(order);
        onlineStore.makeOrderToHome(user, order);
    }

    private void checkCustomerAccount() {
        System.out.println("""
                У вас уже есть аккаунт?
                1. Да
                2. Нет""");
        int max = 2;
        switch (checkChose(max)) {
            case 1 -> {
                System.out.println("Введите имя: ");
                String name = scanner.nextLine();
                System.out.println("Введите пароль: ");
                String password = scanner.nextLine();
                user = onlineStore.findCustomer(name, password);
            }
            case 2 -> {
                user = onlineStore.registerUser();
                System.out.println("Поздравляю вы зарегистрированы!");
            }
        }
        onlineMarketInterface();
    }

    //DRY
    private Order makeOrder(Store store) {
        OrderBuilder orB = new OrderBuilder(store.getName());
        while (!scanner.nextLine().equalsIgnoreCase("exit")) {
            showProducts(store);
            System.out.println("Введите имя товара, чтобы узнать информацию о нём: ");
            String productName = scanner.nextLine();
            System.out.println(store.FindProductByName(productName));
            System.out.println("""
                    Добавить товар в корзину?
                    1. Да
                    2. Нет""");
            int max = 2;
            int chose = checkChose(max);
            if (chose == 1) {
                orB.addProduct(store.getProduct(productName));
                System.out.println("Продукт добавлен");
            }
            System.out.println("Чтобы закончить покупки введите exit");
        }
        return orB.build();
    }
    //DRY
    private void showProducts(Store store) {
        System.out.println("""
                Хотите посмотреть все товары или по типам:
                1. Все товары
                2. По типу""");
        int max = 2;
        switch (checkChose(max)) {
            case 1 -> {
                System.out.println("Список товаров:");
                store.AllProducts().forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("Типы товаров в магазине");
                store.getProductTypes().forEach(System.out::println);
                System.out.println("Выберите тип товара");
                String type = scanner.nextLine();
                System.out.println("Товары типа " + type);
                store.ProductsByType(type).forEach(System.out::println);
            }

        }
    }
}
