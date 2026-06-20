package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] items;
    private int size;

    public SearchEngine(int capacity) {
        this.items = new Searchable[capacity];
        this.size = 0;
    }

    public void add(Searchable item) {
        if (size < items.length) {
            items[size] = item;
            size++;
        } else {
            System.out.println("Неа!");
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int found = 0;

        for (int i = 0; i < size && found < 5; i++){
            Searchable current = items[i];
            String searchTerm = current.getSearchTerm().toLowerCase();
            if (searchTerm.contains(query.toLowerCase())){
                results[found] =  current;
                found++;
            }
        }
        return results;
    }
}