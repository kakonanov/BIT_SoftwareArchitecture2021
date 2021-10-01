package ru.sber.arch.task1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ConcurrentLRUCacheTest {

    @Test
    public void simpleTest() {
        Cache<Integer, Integer> cache = new ConcurrentLRUCache<>(7);

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

    @Test
    public void test() throws InterruptedException {
        final int size = 10000;
        Cache<Integer, Integer> concCache = new ConcurrentLRUCache<>(size);
        Cache<Integer, Integer> cache = new LRUCache<>(size);

        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(size);

        try {
            IntStream.range(0, size).<Runnable>mapToObj(key -> () -> {
                cache.put(key, key);
                concCache.put(key, key);
                countDownLatch.countDown();
            }).forEach(executorService::submit);
            countDownLatch.await();
        } finally {
            executorService.shutdown();
        }
        assertNotEquals(cache.getValues().size(), size);
        assertEquals(concCache.getValues().size(), size);
    }
}
