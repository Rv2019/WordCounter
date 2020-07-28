package com.wordcounter.utils;

/**
 * @author rv
 * WordCounter
 */
public class WordCounter {

	/**
	 * Translation
	 */
	private Translation translator;
	/**
	 * WordContainer
	 */
	private WordContainer wordContainer;

	/**
	 * Word Counter constructor
	 * @param translator
	 */
	public WordCounter(Translation translator) {
		this.translator = translator;
		this.wordContainer = new WordContainer();
	}

	/**
	 * Add a word
	 * @param word
	 */
	public void addWord(String word) {
		Filter.validateWord.apply(word);
		wordContainer.put(translator.translate(word).toLowerCase(), word);
	}

	/**
	 * Count Word
	 * @param word
	 * @return int
	 */
	public int countWord(String word) {
		return wordContainer.getWordCount(translator.translate(word).toLowerCase());
	}
}