package ru.sber.arch.task1;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> implements Cache<K, V> {
    private final int size;

    public LRUCache(int size) {
        super(size, 1.f, true);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > size;
    }

    @Override
    public Collection<V> getValues() {
        return super.values();
    }
}
