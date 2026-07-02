package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> items;

    public SearchEngine(int capacity) {
        this.items = new ArrayList<>(capacity);
    }

    public void add(Searchable item) {
        if (item == null) {
            throw new NullPointerException("Объект не может быть null");
        }
        items.add(item);
    }

    public List<Searchable> search(String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Запрос не может быть null или пустым");
        }
        List<Searchable> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();
        for (Searchable current : items) {
            if (current.getSearchTerm().toLowerCase().contains(lowerQuery)) {
                results.add(current);
            }
        }
        return results;
    }

    public Searchable relevant(String query) throws BestResultNotFound {
        if (query == null || query.trim().isEmpty()) {
            throw new BestResultNotFound(query);
        }
        Searchable bestMatch = null;
        int maxCount = 0;
        String lowerQuery = query.toLowerCase();
        for (Searchable current : items) {
            int count = countOccurrences(current.getSearchTerm().toLowerCase(), lowerQuery);
            if (count > maxCount) {
                maxCount = count;
                bestMatch = current;
            }
        }
        if (bestMatch == null || maxCount == 0) {
            throw new BestResultNotFound(query);
        }
        return bestMatch;
    }

    private int countOccurrences(String text, String sub) {
        if (text == null || sub == null || sub.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }
        return count;
    }

    public int getSize() {
        return items.size();
    }
}