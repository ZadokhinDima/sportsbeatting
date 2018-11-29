package com.epam.training.sportsbeatting.repository;

import java.util.Collection;
import java.util.List;

import com.epam.training.sportsbeatting.domain.PersistableObject;


public interface GenericDao<T extends PersistableObject> {

    void save(T item);

    void saveAll(Collection<T> items);

    T get(Long id);

    List<T> getAll();

    void remove(T item);

    void refresh(T item);

    void removeAll(Collection<T> items);

}
