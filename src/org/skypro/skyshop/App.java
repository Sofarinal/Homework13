package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.ProductBasket;

public class App {
    public static void main(String[] args) {

        Product apple = new Product("Яблоко", 50);
        Product banana = new Product("Банан", 80);
        Product milk = new Product("Молоко", 120);
        Product bread = new Product("Хлеб", 60);
        Product cheese = new Product("Сыр", 350);
        Product chocolate = new Product("Шоколад", 150);


        ProductBasket basket = new ProductBasket();

        System.out.println("========== ДЕМОНСТРАЦИЯ РАБОТЫ КОРЗИНЫ ==========\n");


        System.out.println("1. Добавляем продукты в корзину:");
        basket.addProduct(apple);
        basket.addProduct(banana);
        basket.addProduct(milk);
        basket.addProduct(bread);
        basket.addProduct(cheese);
        System.out.println("   Добавлено 5 продуктов");


        System.out.println("\n2. Пытаемся добавить 6-й продукт (Шоколад):");
        basket.addProduct(chocolate);


        System.out.println("\n3. Содержимое корзины:");
        basket.printBasket();


        System.out.println("\n4. Общая стоимость корзины: " + basket.getTotalPrice());


        System.out.println("\n5. Поиск товара 'Молоко':");
        System.out.println("   Результат: " + (basket.containsProduct("Молоко") ? "найден" : "не найден"));


        System.out.println("\n6. Поиск товара 'Печенье':");
        System.out.println("   Результат: " + (basket.containsProduct("Печенье") ? "найден" : "не найден"));


        System.out.println("\n7. Очищаем корзину:");
        basket.clearBasket();
        System.out.println("   Корзина очищена");

        System.out.println("\n8. Содержимое пустой корзины:");
        basket.printBasket();


        System.out.println("\n9. Общая стоимость пустой корзины: " + basket.getTotalPrice());


        System.out.println("\n10. Поиск товара 'Яблоко' в пустой корзине:");
        System.out.println("    Результат: " + (basket.containsProduct("Яблоко") ? "найден" : "не найден"));

        System.out.println("\n========== ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ==========");
    }
}
