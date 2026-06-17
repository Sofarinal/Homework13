package org.skypro.skyshop.product;

public class ProductBasket {
    private final Product[] products;
    private int size;

    public ProductBasket() {
        this.products = new Product[5];
        this.size = 0;
    }

    public void addProduct(Product product) {
        if (size >= products.length) {
            System.out.println("Невозможно добавить продукт");
            return;
        }
        products[size] = product;
        size++;
    }

    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            if (products[i] != null) {
                total += products[i].getPrice();
            }
        }
        return total;
    }

    public void printBasket() {
        if (size == 0) {
            System.out.println("в корзине пусто");
            return;
        }
        int specialProduct = 0;
        for (int i = 0; i < size; i++) {
            Product product = products[i];
            System.out.println(product.toString());
            if (product.isSpecial()){
                specialProduct++;
            }
        }
        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialProduct);
    }

    public boolean containsProduct(String name) {
        for (int i = 0; i < size; i++) {
            if (products[i] != null && products[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        for (int i = 0; i < size; i++) {
            products[i] = null;
        }
        size = 0;
    }
}