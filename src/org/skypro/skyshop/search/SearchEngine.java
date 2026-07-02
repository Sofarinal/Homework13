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
            System.out.println("Неа! ");
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

    public Searchable relevant (String query) throws BestResultNotFound{
        if (query == null || query.isEmpty()){
            throw new BestResultNotFound(query);
        }

        Searchable coincidence = null;
        int maxCount = 0;
        String lowerQuery = query.toLowerCase();

        for (int i = 0; i < size; i++) {
            Searchable current = items[i];
            String term = current.getSearchTerm().toLowerCase();
            int count = counting(term, lowerQuery);
            if (count > maxCount){
                maxCount = count;
                coincidence = current;
            }
        }
        if (coincidence == null){
            throw new BestResultNotFound(query);
        }
        return coincidence;
    }

    private int counting (String text, String sub){
        if (sub.isEmpty()) return 0;
        int count = 0;
        int index = 0;

        while ((index = text.indexOf(sub, index)) != -1){
            count++;
            index += sub.length();
        }
        return count;
    }
}