package com.epam.training.sportsbeatting.repository.memory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.training.sportsbeatting.repository.GenericDao;

public abstract class GenericInMemoryDao<T> implements GenericDao<T> {

    protected Long autoIncrement = 0L;
    protected Map<Long, T> storage = new HashMap<>();

    @Override
    public void save(final T item) {
        autoIncrement++;
        storage.put(autoIncrement, item);
    }

    @Override
    public T get(final Long id) {
        return storage.get(id);
    }

    @Override
    public void saveAll(final Collection<T> items) {
        items.forEach(this::save);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void removeAll(final Collection<T> items) {
        items.forEach(this::remove);
    }
}
