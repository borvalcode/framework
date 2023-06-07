package com.borvalcode.framework.domain.repository.repository;

import java.util.Optional;

import com.borvalcode.framework.domain.entity.Entity;

public interface Repository<K extends Entity.Key, V extends Entity> {

    Optional<V> get(K key);

    void store(V value);
}
