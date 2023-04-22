import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> {

    // Cache capacity
    private int capacity;

    // LinkedHashMap to maintain order of elements in cache
    private Map<K, V> cache;

    // Constructor to initialize the cache with the specified capacity
    public LRUCache(int capacity) {
        this.capacity = capacity;

        // Initialize cache with access-ordering
        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                // Remove the least recently used entry if cache size exceeds capacity
                return size() > capacity;
            }
        };
    }

    // Method to get a value from the cache
    public synchronized V get(K key) {
        return cache.get(key);
    }

    // Method to add a key-value pair to the cache
    public synchronized void put(K key, V value) {
        cache.put(key, value);
    }

    // Method to remove a key-value pair from the cache
    public synchronized void remove(K key) {
        cache.remove(key);
    }

    // Method to clear the cache
    public synchronized void clear() {
        cache.clear();
    }

    // Method to get the current size of the cache
    public synchronized int size() {
        return cache.size();
    }

}
