package com.wordcounter;

import java.util.Map;

import com.wordcounter.utils.AppConfiguration;
import com.wordcounter.utils.WordCounter;

/**
 * StarterApp Main class
 * 
 * @author rv
 *
 */
public class StarterApp {

	static Map<String, String> dummyWords = AppConfiguration.wordsdictionary;

	public static void main(String[] args) {
		WordCounter wordCounter = new WordCounter((word) -> dummyWords.get(word));
		dummyWords.keySet().stream().forEach(word -> {
			System.out.println("Adding " + word);
			wordCounter.addWord(word);
		});
		dummyWords.keySet().stream().forEach(word -> {
			System.out.println("Counting for " + word + " " + wordCounter.countWord(word));
		});
	}
}
