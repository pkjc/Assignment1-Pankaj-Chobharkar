package com.ouassignments.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import com.ouassignments.services.WordService;
import com.ouassignments.view.HangmanView;

public class KeyboardActionListener implements ActionListener {
	private HangmanView hangmanView;
	private WordService wordService;
	//private StringBuilder guess = new StringBuilder();
	//private int count;
	int addedCount = 0;
	
	public KeyboardActionListener(HangmanView hangmanView, WordService wordService) {
		super();
		this.wordService = wordService;
		this.hangmanView = hangmanView;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		StringBuilder guess = new StringBuilder();
		JButton whichButton = (JButton) evt.getSource();
		String clickedButton = whichButton.getActionCommand();
		addedCount = hangmanView.getCount();
		hangmanView.getGuessAttempts().setText(++addedCount + " Tries");
		hangmanView.setCount(addedCount);
		String guessString = hangmanView.getWordGuessField().getText();
		guess.append(guessString);
		
		String pickedWord = hangmanView.getPickedWord();
		
		guess.setLength(pickedWord.length());
		revealGuessedChars(clickedButton, pickedWord, guess);

		endGameAfterUserWon(pickedWord, guess, addedCount);
	}

	private void endGameAfterUserWon(String pickedWord, StringBuilder guess, int addedCount) {
		if (hangmanView.getWordGuessField().getText().equalsIgnoreCase(pickedWord)) {
			hangmanView.getGuessAttempts().setText("Congrats! You won Hangman in " + addedCount + " Tries!");
			for (JButton keyBtn : hangmanView.getKeyboardList()) {
				keyBtn.setEnabled(false);
			}
			guess.delete(0, guess.length());
			addedCount=0;
		}
	}

	private void revealGuessedChars(String clickedButton, String pickedWord, StringBuilder guess) {
		List<Integer> indexOfChar = wordService.checkGuess(clickedButton, pickedWord);
		for (int i = 0; i < indexOfChar.size(); i++) {
			if (!indexOfChar.isEmpty()) {
				guess.setCharAt(indexOfChar.get(i), clickedButton.charAt(0));
			}
		}
		hangmanView.getWordGuessField().setText(guess.toString());
	}
}