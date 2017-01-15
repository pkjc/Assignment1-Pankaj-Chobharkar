package com.ouassignments.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.ouassignments.services.WordService;
import com.ouassignments.view.HangmanView;

public class StartStopActionListener implements ActionListener {
	HangmanView hangmanView;
	WordService wordService;

	public StartStopActionListener(HangmanView hangmanView, WordService wordService) {
		this.hangmanView = hangmanView;
		this.wordService = wordService;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton whichButton = (JButton) event.getSource();
		String cmd = whichButton.getActionCommand();
		StringBuilder charSpaces = new StringBuilder();

		if (cmd.equals("Start Game")) {
			hangmanView.getStartStopGameBtn().setText("Stop Game");
			String randomWord = wordService.getRandomWord();
			hangmanView.setPickedWord(randomWord);

			for (int i = 0; i < randomWord.length(); i++) {
				charSpaces.append("-");
			}
			hangmanView.getWordGuessField().setText(charSpaces.toString());

			for (JButton keyBtn : hangmanView.getKeyboardList()) {
				keyBtn.setEnabled(true);
			}
		} else if (cmd.equals("Stop Game")) {
			hangmanView.getWordGuessField().setText("XXXXXXXXXXXXXXXX");
			hangmanView.getGuessAttempts().setText("0 Tries");
			for (JButton keyBtn : hangmanView.getKeyboardList()) {
				keyBtn.setEnabled(false);
			}
			charSpaces.delete(0, charSpaces.length());
			hangmanView.getStartStopGameBtn().setText("Start Game");
			hangmanView.setCount(0);
		}
	}
}