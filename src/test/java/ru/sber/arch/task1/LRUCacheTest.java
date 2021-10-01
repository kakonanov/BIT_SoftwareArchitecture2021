package ru.sber.arch.task1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {
    @Test
    public void test(){
        Cache<Integer, Integer> cache = new LRUCache<>(7);
        for (int i = 0; i < 7; i++) {
            cache.put(i, i);
        }
        assertEquals(List.of(0, 1, 2, 3, 4, 5, 6), new ArrayList<>(cache.getValues()));
        cache.put(10, 10);
        cache.put(12, 12);
        assertEquals(2, cache.get(2));
        assertNull(cache.get(30));
        cache.put(25, 25);
        assertEquals(List.of(4, 5, 6, 10, 12, 2, 25), new ArrayList<>(cache.getValues()));
    }
}