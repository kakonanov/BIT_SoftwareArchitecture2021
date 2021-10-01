package ru.sber.arch.task1;

import java.util.Collection;

public interface Cache<K, V> {
    V put(K key, V value);

    V get(K key);

    Collection<V> getValues();
}
