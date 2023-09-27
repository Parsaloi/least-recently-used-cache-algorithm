# The Parsaloi LRUCache Algorithm implementation (least-recently-used-cache-algorithm)

- This is a Parsa's implementation of the Least Recently Used (LRU) cache algorithm written in Java that supports further concurrent implementations of the LRU Cache algorithm

- With this implementation order of items in the cache does not matter 😜

- With this implementation only one instance of a cache item access can exist at a time to avoid conflicts. 
- This we have resolved by making the required methods synchronized to allow for access by multiple tbreads

## Engineer's Parsaloi implementation so far: 

- I have implemented a LRUCache class backed by a HashMap and LinkedList and implemented the methods to add a cache item to a full cache list by hand

- This implementation focuses on optimization of finding the least recently used cache item using a timestamp value stored in the cacheList 

## Lessons learnt by Engineer Parsaloi
> 🥸

- I have learnt alot about `synchronization`, *the costs of unecessary object creation*  

- Parsaloi has improved his skill in writing of generic classes and methods

> I have also learnt about the LinkedHashMap data structure that has an in-built method of removing the eldest item in the datastructure called  
removeEldestEntry that you can override to get the desired behavior


> > I enjoyed working on this first draft to my quest to write an efficient LRU cache algorithm!


