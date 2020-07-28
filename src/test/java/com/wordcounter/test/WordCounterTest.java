package com.wordcounter.test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.wordcounter.utils.WordCounter;

/**
 * @author rv WordCounterTest
 */
public class WordCounterTest {

	/**
	 * WordCounter
	 */
	WordCounter wordCounter;

	@Before
	public void init() {
		wordCounter = new WordCounter((word) -> dummyContainer.get(word));
	}

	/**
	 * Test data
	 */
	private static final Map<String, String> dummyContainer;

	static {
		dummyContainer = new HashMap<>();
		dummyContainer.put("flower", "flower");
		dummyContainer.put("flor", "flower");
		dummyContainer.put("blume", "flower");
		dummyContainer.put("testInGerman", "test");
		dummyContainer.put("testInFrance", "test");
		dummyContainer.put("raj", "raj");
		dummyContainer.put("rv", "rv");
		dummyContainer.put("rajesh", "rajesh");
		dummyContainer.put("test", "test");
	}

	/**
	 * testAdd_Alphabets_ExpectingNoException Postive Test case
	 */
	@Test
	public void testAdd_Alphabets_ExpectingNoException() {
		wordCounter.addWord("raj");
		wordCounter.addWord("rv");
		wordCounter.addWord("rajesh");
	}

	/**
	 * testCount_WordInMultipleLanguageAdded_ExpectValidCount Postive Test case
	 */
	@Test
	public void testCount_WordInMultipleLanguageAdded_ExpectValidCount() {
		wordCounter.addWord("flower");
		wordCounter.addWord("flor");
		wordCounter.addWord("blume");
		Assert.assertEquals(3, wordCounter.countWord("flor"));
		Assert.assertEquals(3, wordCounter.countWord("blume"));
		Assert.assertEquals(3, wordCounter.countWord("flor"));
		wordCounter.addWord("testInGerman");
		wordCounter.addWord("testInFrance");
		Assert.assertEquals(2, wordCounter.countWord("testInGerman"));
		Assert.assertEquals(2, wordCounter.countWord("testInFrance"));
	}

	/**
	 * testCount_NonEnglishWordAdded_EngWordUsedCount_ThenExpectACount Positive Test
	 * case
	 */
	@Test
	public void testCount_NonEnglishWordAdded_EngWordUsedCount_ThenExpectACount() {
		wordCounter.addWord("testInGerman");
		Assert.assertEquals(1, wordCounter.countWord("test"));
	}

	/**
	 * testCount_WhenAWordAddedMultipleTimes_ExpectValidCount Positive Test case
	 */
	@Test
	public void testCount_WhenAWordAddedMultipleTimes_ExpectValidCount() {
		IntStream.range(0, 10).forEach(i -> wordCounter.addWord("flower"));
		Assert.assertEquals(10, wordCounter.countWord("flower"));
		Assert.assertEquals(10, wordCounter.countWord("flor"));
	}

	/**
	 * Negative test case testAdd_GivenAlphaNumericWord_ExpectingException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAdd_GivenAlphaNumericWord_ExpectingException() {
		wordCounter.addWord("rajesh1123");
		wordCounter.addWord("rv1123");
		wordCounter.addWord("1123AAA");
	}

}
