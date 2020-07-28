package com.wordcounter.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rv
 * DummyWords class 
 */
public class AppConfiguration {

	 public static Map<String, String> wordsdictionary = new HashMap<>();

	    static {
	        wordsdictionary.put("flower","flower");
	        wordsdictionary.put("flor","flower");
	        wordsdictionary.put("blume","flower");
	    }
}