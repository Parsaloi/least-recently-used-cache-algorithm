package app.creatorp.lrucache;

//import static org.junit.Assert.assertEquals;
//import org.junit.Before;
//import org.junit.Test;

import java.util.*;
//import app.creatorp.lrucache.LRUCache;

public class LRUCacheTest {
    public static void main (String [] args) {
        // Create a cache with a capacity of 3
        LRUCache<String, String> cache = new LRUCache<>(3);

        // Add 3 items to the cache
        cache.put("one", "nabo");
        cache.put("two", "are");
        cache.put("three", "uni");

        // print the cache contents
        System.out.println("Cache contents after adding 3 items: " + cache.getCacheList());

        // add a 4th item to the cache, which will cause the oldest item ("one") to be removed
        cache.put("four", "ongwan");

        //print the cache contents
        System.out.println("Cache contents after adding 4th item: " + cache.getCacheList());

        // get the value of the lest recently used item ("two") from the cache which will make it the most recently used
        String lruValue = cache.get("two");

        //print the cache contents
        System.out.println("Cache contents after getting least recently used item: " + cache.getCacheList());
        System.out.println("Value of key two: " + lruValue);
    }
}
