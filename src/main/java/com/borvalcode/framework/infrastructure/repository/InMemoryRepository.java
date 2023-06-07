package com.borvalcode.framework.infrastructure.repository;

import java.util.Map;
import java.util.Optional;

import com.borvalcode.framework.domain.entity.Entity;
import com.borvalcode.framework.domain.repository.repository.Repository;

public class InMemoryRepository<K extends Entity.Key, V extends Entity<K>>
        implements Repository<K, V> {
    private final Map<K, V> storage;

    public InMemoryRepository(Map<K, V> storage) {
        this.storage = storage;
    }

    @Override
    public Optional<V> get(K key) {
        return storage.values().stream().filter(v -> v.getKeys().contains(key)).findFirst();
    }

    @Override
    public void store(V value) {
        storage.put(value.getPrimaryKey(), value);
    }
}
