package com.epam.training.sportsbeatting.repository;

import java.util.Collection;
import java.util.List;

public interface GenericDao<T> {

    void save(T item);

    void saveAll(Collection<T> items);

    T get(Long id);

    List<T> getAll();

    void remove(T item);

    void removeAll(Collection<T> items);

}
