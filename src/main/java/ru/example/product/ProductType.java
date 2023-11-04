package ru.example.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProductType {
    LACTIC("Молочка"), MEAT("Мясо"),
    FISH("Рыба"), CANDIES("Сладости"),
    SNACKS("Снеки"), VEGETABLES("Овощи"),
    FRUITS("Фрукты"), GREATS("Крупы");
    @Getter
    private final String description;
}