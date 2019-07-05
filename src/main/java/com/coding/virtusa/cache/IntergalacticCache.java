package com.coding.virtusa.cache;

import java.util.HashMap;
import java.util.Map;

public class IntergalacticCache<K, T> {

	private  Map<K, T> cache;

	public IntergalacticCache() {
		cache = new HashMap<>();
	}

	public void addInCache(K key, T info) {
		cache.put(key, info);
	}

	public T fetchFromCache(K key) {
		return cache.get(key);
	}
	
	public void removeFromCache(K key) {
		cache.remove(key);
	}
	

}
