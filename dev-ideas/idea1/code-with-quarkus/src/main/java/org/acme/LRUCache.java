import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> {
    // Maximum capacity of cache
    private final int maxCapacity;

    // map to store key-value pairs
    private final Map<K,V> cacheMap;

    // Contructor to initialize LRUCache with a maximum capacity
    public LRUCache(int maxCapacity) {
	this.maxCapacity = maxCapacity;
	this.cacheMap = new LinkedHashMap<K, V>(maxCapacity, 0.75f, true) {
		@Override
		protected booloean removeEldestEntry(Map.Entry<K, V> eldest) {
		    return size() > maxCapacity;
		}	
	    };
       }

    // get methods
    public HashMap<K, V> getCacheMao() {
	return cacheMap;
    }

    public List<CacheItem> getCacheList() {
	return cacheList;
    }
    
    // method to add a cache item
    public void put(K key, V value) {
	cacheMap.put(key, value);
    }

    // method to get cache item
    public V get (K key) {
	return cacheMap.get(key);
    }

    // method to remove a cache item
    public void remove (K key) {
	cacheMap.remove(key);
    }
}
