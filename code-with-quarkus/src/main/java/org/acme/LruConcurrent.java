/**
 *The LRU cache will be implemented using a linked-list and a hash-table. The
 *linked-list will hold items in the cache and the hash-table will be used
 *for efficient look-ups. We will initialize the linked-list for the LRU cache
 *with a size of n; therefore the cache will only hold a total number of n items
 *When the cache is full and we want to add a new item to the cache list, we
 *we have to remove the least recently used item. Given that any item in the
 *cache might be the least recently used item we will attach a timestamp to
 *each item in the cache list. Here we will use a hash-table to map timestamp
 *values (as keys) to cache-item value (as value). This will allow us to know
 *which item in the cache list regardless of their position in the list is the
 *least recently used item. In order to find the least recently used item in the
 *cache we will carry out a synchronized look-up operations using threads to try
 *achieve a O(1) time complexity. The Java programming language provides a
 *Collections.synchronizedList() method that returns a synchronized (thread-safe)
 *list backed by the specialist list; all accesses to our hash-table  will be synchronized.
 *We will used the synchronized list to iterate over the timestamp values while looking for the
 *latest timestamp in the synchronized list using a condition to compare the timestamp
 *values and return the latest timestamp. We will the use the returned timestamp value to return the
 *value of the cache item from the hash-map which will happen in 0(1) as we are using a Hash table.
 **/
