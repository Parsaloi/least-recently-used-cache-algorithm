# least-recently-used-cache-algorithm
This is a concurrent implementation of the Least Recently Used (LRU) cache algorithm written in Java
With this implementation order of items in the cache does not matter

With this implementation only one instance of a cache item access can exist at a time to avoid conflicts...

My implementation so far: 

I have implemented a LRUCache class backed by a HashMap and LinkedList and implemented the methods to add a cache item to a full cache list by hand

This implementation focuses on optimization of finding the least recently used cache item using a timestamp value stored in the cacheList 

We have learnt alot about synchronization, uncessary object creation as well improved our writing of generic classes and methods

We have also learnt about the LinkedHashMap data structure that has an in-built method of removing the eldest item in the datastructure called removeEldestEntry that you can override to get the desired behavior


I enjoyed working on this first draft of my quest to write an efficient LRU cache algorithm!


