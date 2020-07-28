package com.wordcounter.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.wordcounter.utils.Translation;
import com.wordcounter.utils.WordContainer;

// @RunWith attaches a runner with the test class to initialize the test data
@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class WordContainerTest {

	// @InjectMocks annotation is used to create and inject the mock object
	@InjectMocks
	WordContainer wordContainer = new WordContainer();

	// @Mock annotation is used to create the mock object to be injected
	@Mock
	Translation translator;

	/**
	 * Scenario Count When Valid Words Added
	 */
	@Test
	public void testGetWordCountWhenValidWordsAdded_ExpectValidCount() {
		wordContainer.put("test", "test");
		wordContainer.put("test1", "testInFrance");
		// add more
		wordContainer.put("test1", "test");
		wordContainer.put("test1", "test");
		wordContainer.put("test", "testInGerman");

		Assert.assertEquals(2, wordContainer.getWordCount("test"));
		Assert.assertEquals(3, wordContainer.getWordCount("test1"));
	}

	/**
	 * Scenario Count When No Words Added
	 */
	@Test
	public void testGetWordCountWhenNoWordsAdded_ExpectZeroCount() {
		Assert.assertEquals(0, wordContainer.getWordCount("test"));
		Assert.assertEquals(0, wordContainer.getWordCount("test1"));
	}

	@Test(expected = NullPointerException.class)
	public void testPut_GivenWhenKeyIsNullThenThrowNullPointerException() {
		wordContainer.put(null, "test");
	}

	/**
	 * Condition WhenGivenValueIsNull
	 */
	@Test(expected = NullPointerException.class)
	public void testPut_WhenGivenValueIsNullThenThrowNullPointerException() {
		wordContainer.put("test", null);
	}

	/**
	 * Condition WhenMultithreads
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Test
	public void testPut_CountWhenMultiple_Threads() throws ExecutionException, InterruptedException {

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		Collection<Future<Integer>> futures = new ArrayList<>(10);

		for (int t = 0; t < 10; t++) {
			futures.add(executorService.submit(() -> {
				wordContainer.put("test", "test");
				return wordContainer.getWordCount("test");
			}));
		}
		
		Set<Integer> wordCount = new HashSet<>();
		for (Future<Integer> f : futures) {
			wordCount.add(f.get());
		}
		Assert.assertEquals(10, wordCount.size());
		
		
		executorService.shutdown();
	}

}