package eng.parsaloi.lrucache.api;

import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @version 1.0.3  2023-09-27
 * @author parsaloi35@gmail.com Parsaloi Nakuoh
 */

/**
 * A {@code LRUCache} object represents a least recently used (LRU) cache
 * An LRU Cache is a data structure that stores a fixed number of items
 * If a new item is added to the cache when it is full, the least recently used item is removed to allow the new item to be stored
 *
 * @param <K> The type of the cache keys
 * @param <V> The type of the cache values
 */
public class LRUCache<K, V> {

    private final int capacity;
    private final Map<K, CacheItem<K, V>> cacheMap;
    private final List<CacheItem<K, V>> cacheList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<K, CacheItem<K, V>>(capacity);
        this.cacheList = Collections.synchronizedList(new LinkedList<CacheItem<K, V>>());
    }

    /**
     * Retirives a cache item
     *
     * @param key The key of the cache item to retrieve
     * @return The cache item, or 'null' if the item is not found in the cache
     */
    public synchronized V get(K key) {
        CacheItem<K, V> item = cacheMap.get(key);

        if (item == null)
            return null;

        // update the timestamp for the accessed item
        item.setTimestamp(System.currentTimeMillis()); //Update the timestamp of the accessed item
        return item.getValue();
    }

    /**
     * Adds a cache item to the cache
     *
     * @param key The key of the cache item to add
     * @param value The value of the cache item to add
     */
    public synchronized void put(K key, V value) {
        CacheItem<K, V> item = cacheMap.get(key);

        if (item == null) {
            // Add new item to the cache
            item =  new CacheItem<K, V>(key, value);
            cacheMap.put(key, item);
            cacheList.add(item);
            
            if (cacheList.size() > capacity) {
                // if cache is full, remove the least recently used item
                CacheItem<K, V> eldestItem = findOldestCacheItem();
                cacheMap.remove(eldestItem.getKey());
                cacheList.remove(eldestItem);
            }
        } else {
            // update existing item in the cache
            item.setValue(value);
            item.setTimestamp(System.currentTimeMillis()); //update the timestamp of the item
            cacheList.remove(item); // removes the current item
            cacheList.add(item); // adds a new instanve of the same item
        }
    }

    /**
     * Finds the oldest item
     *
     * @return The oldest cache item
     */
    private synchronized CacheItem<K, V> findOldestCacheItem() {
        CacheItem<K, V> eldestItem = cacheList.get(0);
        for (CacheItem<K, V> item : cacheList) {
            if (item.getTimestamp() < eldestItem.getTimestamp()) {
                eldestItem = item;
            }
        }
        return eldestItem;
    }

    public synchronized Map<K, V> getCacheMap() {
        Map<K, V> map = new HashMap<K,V>(capacity);
        for (Map.Entry<K, CacheItem<K, V>> entry : cacheMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getValue());
        }
        return map;
    }

    public synchronized List<CacheItem<K, V>> getCacheList() {
        return cacheList;
    }

    // private static inner class CacheItem
    private static class CacheItem<K, V> {

        private final K key;
        private V value;
        private long timestamp;

        public CacheItem(K key, V value) {
            this.key = key;
            this.value = value;
            this.timestamp = System.currentTimeMillis();
        }

        public K getKey(){
           return key; 
        }           

        public V getValue(){
            return value;
        }
            

        public void setValue (V value){
            this.value = value;
        }
            

        public long getTimestamp(){
            return timestamp;
        }
            

        public void setTimestamp(long timestamp){
             this.timestamp = timestamp;
        }

        // override the toString method here
        @Override
        public String toString() {
            return "CacheItem{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
        }
    }

    // Basic unit test (ignore this block)
    /*
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
        System.out.println("Value of key two which is now the most recently used item is <-- " + lruValue);

	// add a 5th item to the cache, which will cause the oldest item ("three") to be removed
	cache.put("five", "imiet");

	// print the cache contents to verify
	System.out.println("Cache contents after adding a 5th item: " + cache.getCacheList());
    }
    */
}
