package com.arorasagar.cache.storage;

public interface DataStore<K, V> {
    K save(K key, V value);

    V get(K key);

    K delete(K Key);
}
