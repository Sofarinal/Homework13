package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.Article.Article;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFound;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Product apple = new SimpleProduct("Яблоко", 50);
        Product banana = new DiscountedProduct("Банан", 80, 50);
        Product milk = new SimpleProduct("Молоко", 120);
        Product bread = new FixPriceProduct("Хлеб");
        Product cheese = new SimpleProduct("Сыр", 350);
        Product chocolate = new SimpleProduct("Шоколад", 150);


        ProductBasket basket = new ProductBasket();

        System.out.println("========= ДЕМОНСТРАЦИЯ РАБОТЫ КОРЗИНЫ =========\n");


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

        System.out.println("\n========== ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ==========");

        System.out.println("\n========== ДЕМОНСТРАЦИЯ ИСКЛЮЧЕНИЙ (ПРОВЕРКИ ДАННЫХ) ==========\n");

        try {
            Product badName = new SimpleProduct("", 100);
            System.out.println("Продукт создан (не должно быть видно)");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании продукта: " + e.getMessage());
        }

        try {
            Product badPrice = new SimpleProduct("Яблоко", 0);
            System.out.println("Продукт создан (не должно быть видно)");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании продукта: " + e.getMessage());
        }

        try {
            Product badDiscounted = new DiscountedProduct("Скидка на квартиру в центре Питера", 100, 200);
            System.out.println("Продукт создан (не должно быть видно)");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании продукта: " + e.getMessage());
        }

        try {
            Product correct = new SimpleProduct("Квартира в центре Питера", 100);
            System.out.println("Корректный продукт создан: " + correct.getName());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании продукта: " + e.getMessage());
        }

        System.out.println("\n========== ДЕМОНСТРАЦИЯ МЕТОДА relevant ==========\n");

        try {
            Searchable found = engine.relevant("яблоко");
            System.out.println("Найден подходящий объект для 'яблоко': " + found.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            Searchable found = engine.relevant("арбуз");
            System.out.println("Найден подходящий объект для 'арбуз': " + found.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            Searchable found = engine.relevant("");
            System.out.println("Найден подходящий объект для '' : " + found.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n========== ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ==========");

        System.out.println("\n========== ДЕМОНСТРАЦИЯ УДАЛЕНИЕ КОРЗИНЫ ==========");

        ProductBasket basketForRemove = new ProductBasket();
        basketForRemove.addProduct(new SimpleProduct("Яблоко", 50));
        basketForRemove.addProduct(new SimpleProduct("Банан", 80));
        basketForRemove.addProduct(new SimpleProduct("Молоко", 120));
        basketForRemove.addProduct(new SimpleProduct("Молоко", 150));
        basketForRemove.addProduct(new SimpleProduct("Хлеб", 60));

        System.out.println("\nИсходное содержимое корзины:");
        basketForRemove.printBasket();

        System.out.println("\n1. Удаляем продукт 'Молоко':");
        List<Product> removed = basketForRemove.removeProduct("Молоко");
        System.out.println("   Удалены следующие продукты:");
        if (removed.isEmpty()) {
            System.out.println("   (пусто)");
        } else {
            for (Product p : removed) {
                System.out.println("   - " + p.getName() + " (" + p.getPrice() + " руб.)");
            }
        }

        System.out.println("\nСодержимое корзины после удаления:");
        basketForRemove.printBasket();

        System.out.println("\n2. Удаляем несуществующий продукт 'Печенье':");
        List<Product> removedEmpty = basketForRemove.removeProduct("Печенье");
        System.out.println("   Удалены следующие продукты:");
        if (removedEmpty.isEmpty()) {
            System.out.println("   Список пуст.");
        } else {
            for (Product p : removedEmpty) {
                System.out.println("   - " + p.getName());
            }
        }

        System.out.println("\nСодержимое корзины после попытки удаления несуществующего продукта:");
        basketForRemove.printBasket();

        System.out.println("\n========== ДЕМОНСТРАЦИЯ УДАЛЕНИЯ ЗАВЕРШЕНА ==========");

    }

    private static void printSearchResults(List<Searchable> results) {
        if (results.isEmpty()) {
            System.out.println("  Ничего не найдено.");
        } else {
            for (Searchable item : results) {
                System.out.println("  - " + item.getStringRepresentation());
            }
        }
        System.out.println();
    }
}
