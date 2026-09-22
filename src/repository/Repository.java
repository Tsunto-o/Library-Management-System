package repository;

import model.Identifiable;


import java.util.ArrayList;
import java.util.List;

public class    Repository<T extends Identifiable> {
    private List<T> items;

    public Repository() {
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public T findById(String id) {
        for (T item : items) {
            if (item.getIdentifier().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public List<T> getAll() {
        return items;
    }
}