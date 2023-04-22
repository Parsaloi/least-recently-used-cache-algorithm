import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestLRUCache {

    public static void main (String [] args) {
	// create a new LRUCache with a maximum capacity of 3
	LRUCache<String, String> cache = new LRUCache<>();

	// add 3 cache items with unique keys
	cache.put("key1", "one");
	cache.put("key2", "two");
	cache.put("key3", "three");

	// add a new cache item to a full cache
	cache.put("key4", "four");

	// test cache content
	Map<String, String> cacheMap = cache.getCacheMap();
	List<LRUCache.CacheItem> cacheList = cache.getCacheList();

	// print cache map
	System.out.println("Cache list");
	for (LRUCache.CacheItem item : cacheList) {
	    System.out.println(item.getKey() + " : " + item.getTimestamp());
	}

	// expected output:
	// Cache map:
	// key2 : two
	// key3 : three
	// key4 : four
	// Cache list:
	// key2 : <timestamp>
	// key3 : <timestamp>
	// key4 " <timestamp>

	//note: key1 is removed from cache since it is the eldest cache item
    }
}
