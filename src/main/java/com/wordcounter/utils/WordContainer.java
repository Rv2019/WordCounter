package com.wordcounter.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * WordContainer class
 * 
 * @author rv
 *
 */
public class WordContainer {

	/**
	 * 
	 * Reentrant mutual exclusion Lock
	 */
	final ReentrantLock reentrant = new ReentrantLock();

	/**
	 * Words Container
	 *
	 */
	private Map<String, List<String>> wordsContainer = new ConcurrentHashMap<>();

	/**
	 * Look up and Update
	 * 
	 * @param key
	 * @param value
	 */
	private void Update(String key, String value) {
		reentrant.lock();
		try {
			List<String> words = wordsContainer.getOrDefault(key, new ArrayList<>());
			words.add(value);
			wordsContainer.put(key, words);
		} finally {
			reentrant.unlock();
		}
	}

	/**
	 * Get Word Count
	 * 
	 * @param word
	 * @return
	 */
	public int getWordCount(String word) {
		return wordsContainer.containsKey(word) ? wordsContainer.get(word).size() : 0;
	}

	/**
	 * Update
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		Objects.requireNonNull(value);
		Update(key, value);
	}
}
