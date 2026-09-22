package repository;

import model.Identifiable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository<T extends Identifiable> {
    private final List<T> items;
    private final Map<String, T> itemsById;

    public Repository() {
        this.items = new ArrayList<>();
        this.itemsById = new HashMap<>();
    }

    public void add(T item) {
        items.add(item);
        itemsById.put(item.getIdentifier(), item);
    }

    public T findById(String id) {
        return itemsById.get(id);
    }

    public List<T> getAll() {
        return Collections.unmodifiableList(items);
    }

    public static <T extends Identifiable> Repository<T> of(Collection<? extends T> source) {
        Repository<T> repository = new Repository<>();
        for (T item : source) {
            repository.add(item);
        }
        return repository;
    }
}
