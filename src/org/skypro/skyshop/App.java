package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.Article.Article;
import org.skypro.skyshop.search.Searchable;

public class App {
    public static void main(String[] args) {

       Product apple = new SimpleProduct("Яблоко", 50);
       Product banana = new DiscountedProduct("Банан", 80, 50);
       Product milk = new SimpleProduct("Молоко", 120);
       Product bread = new FixPriceProduct("Хлеб");
       Product cheese = new SimpleProduct("Сыр", 350);
       Product chocolate = new SimpleProduct("Шоколад", 150);


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

        System.out.println("\n========== ДЕМОНСТРАЦИЯ ПОИСКА ==========");

        Article appleArticle = new Article("Яблоко", "Купите мне яблоко");
        Article bananaArticle = new Article("Банан", "Хочу банан");
        Article milkArticle = new Article("Молоко", "Зачем корове молоко?");

        SearchEngine engine = new SearchEngine(15);
        engine.add(apple);
        engine.add(banana);
        engine.add(milk);
        engine.add(bread);
        engine.add(cheese);
        engine.add(chocolate);
        engine.add(appleArticle);
        engine.add(bananaArticle);
        engine.add(milkArticle);

        System.out.println("Запрос: \"яблоко\"");
        printSearchResults(engine.search("яблоко"));

        System.out.println("\nЗапрос: \"молоко\"");
        printSearchResults(engine.search("молоко"));

        System.out.println("\nЗапрос: \"хлеб\"");
        printSearchResults(engine.search("хлеб"));

        System.out.println("\nЗапрос: \"сыр\"");
        printSearchResults(engine.search("сыр"));

        System.out.println("\nЗапрос: \"корове\"");
        printSearchResults(engine.search("корове"));

        System.out.println("\nЗапрос: \"шоколад\"");
        printSearchResults(engine.search("шоколад"));

        System.out.println("\nЗапрос: \"несуществующий\"");
        printSearchResults(engine.search("несуществующий"));

    }

    private static void printSearchResults(Searchable[] results) {
        boolean found = false;
        for (Searchable item : results) {
            if (item != null) {
                System.out.println("  - " + item.getStringRepresentation());
                found = true;
            }
        }
        if (!found) {
            System.out.println("  Ничего не найдено.");
        }
    }
}
