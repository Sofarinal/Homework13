package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Имя не божет ровняться нулю или быть пустым");
        }
        this.name = name;
}


    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public String getSearchTerm(){
        return name;
    }

    @Override
    public String getContent(){
        return "PRODUCT ";
    }
}