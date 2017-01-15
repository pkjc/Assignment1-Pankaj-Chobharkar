package com.ouassignments.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordService {

	private ArrayList<String> words;

	public WordService(String wordlistFileName) {
		words = new ArrayList<String>();
		try {
			Scanner in;
			in = new Scanner(getClass().getClassLoader().getResource(wordlistFileName).openStream());
			while (in.hasNextLine()) {
				String line = in.nextLine().trim();
				if (line.length() > 0)
					words.add(line);
			}
			if (words.size() == 0) {
				in.close();
				throw new IllegalArgumentException("Given File: " + wordlistFileName + " is Empty");
			}
			in.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot read File: " + wordlistFileName);
		}
	}

	public int getWordCount() {
		return words.size();
	}

	public String getRandomWord() {
		String randomWord = words.get((int) (Math.random() * words.size()));
		if (randomWord.length() < 16) {
			return randomWord;
		} else {
			getRandomWord();
		}
		return randomWord;
	}

	public List<Integer> checkGuess(String clickedButton, String pickedWord) {
		List<Integer> indexOfChar = new ArrayList<Integer>(pickedWord.length());

		for (int count = 0; count < pickedWord.length(); count++) {
			if (pickedWord.charAt(count) == clickedButton.toLowerCase().charAt(0)) {
				indexOfChar.add(count);
			}
		}
		return indexOfChar;
	}
}