package org.skypro.skyshop.product;

public class DiscountedProduct extends Product{
    private final int basePrice;
    private final int discount;

    public DiscountedProduct (String name, int basePrice, int discount){
        super(name);
        if (discount < 0 || discount > 100){
            throw new IllegalArgumentException("Скидка не может быть меньше 0 и больше 100%");
        }
        if (basePrice <= 0){
            throw new IllegalArgumentException("Цена продукта не может быть меньше или равна 0");
        }
        this.basePrice = basePrice;
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return basePrice * (100 - discount) / 100;
    }

    @Override
    public boolean isSpecial(){
        return true;
    }

    @Override
    public String toString(){
        return getName() + ": " + getPrice() + " (" + discount + " %)";
    }
}