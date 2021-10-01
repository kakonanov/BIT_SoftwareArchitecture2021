package ru.sber.arch.task1;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentLRUCache<K, V> extends LinkedHashMap<K, V> implements Cache<K, V> {
    private final int size;

    public ConcurrentLRUCache(int size) {
        super(size, 1.f, true);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > size;
    }

    @Override
    public Collection<V> getValues() {
        synchronized (this) {
            return super.values();
        }
    }

    @Override
    public V put(K key, V value) {
        synchronized (this) {
            return super.put(key, value);
        }
    }

    @Override
    public V get(Object key) {
        synchronized (this) {
            return super.get(key);
        }
    }
}
