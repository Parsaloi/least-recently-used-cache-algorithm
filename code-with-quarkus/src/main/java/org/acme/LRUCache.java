import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache<K, V> {

    private final int capacity;
    private final Map<K, CacheItem<K, V>> cacheMap;
    private final List<CacheItem<K, V>> cacheList;

    public LRUCache(int capacity) {
	this.capacity = capacity;
	this.cacheMap = new HashMap<K, CacheItem<K, V>>(capacity);
	this.cacheList = Collections.synchronizedList(new LinkedList<CacheItem<K, V>>());
    }

    // retrive a cache item
    public synchronized V get(K key) {
	CacheItem<K, V> item = cacheMap.get(key);

	if (item == null)
	    return null;

	// update the timestamp for the accessed item
	item.setTimestamp(System.currentTimeMillis()); //Update the timestamp of the accessed item
	return item.getValue();
    }

    // add a cache item
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
		// revisit to understand how this removal happens??
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

    // find the oldest cache item
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
}
