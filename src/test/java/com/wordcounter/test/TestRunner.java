package com.wordcounter.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(WordContainerTest.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());

		Result resultCounter = JUnitCore.runClasses(WordCounterTest.class);

		for (Failure failure : resultCounter.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(resultCounter.wasSuccessful());

	}
}