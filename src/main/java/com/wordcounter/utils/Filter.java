package com.wordcounter.utils;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * @author rv
 * Filter class which validates in static context for the input string characters for alphabets 
 */
public class Filter {

	/**
	 * Pattern for alphabet only
	 */
	private static final Pattern allowedWordChars = Pattern.compile("[a-zA-Z]+$");

	/**
	 * checks allowed Word Chars are valid matching with the value
	 */
	private static Predicate<String> isValid = val -> allowedWordChars.matcher(val).find();

	/**
	 * 
	 */
	public static Function<String, String> validateWord = (val) -> {
		if (!isValid.test(val))
			throw new IllegalArgumentException("only alphabets characters are allowed");
		return val;
	};
}


