package com.ouassignments.main;

import javax.swing.JButton;

import com.ouassignments.listeners.KeyboardActionListener;
import com.ouassignments.listeners.StartStopActionListener;
import com.ouassignments.services.WordService;
import com.ouassignments.view.HangmanView;

public class Hangman {

	public static void main(String[] args) {
		HangmanView hangmanView = new HangmanView();
		WordService wordService = new WordService("resources/wordlist.txt");

		// Initialize Game Board
		hangmanView.initGameboard();

		// Add Start/Stop Action Listener
		StartStopActionListener startStopActionListener = new StartStopActionListener(hangmanView, wordService);
		hangmanView.getStartStopGameBtn().addActionListener(startStopActionListener);

		// Add Keyboard Action Listener
		KeyboardActionListener keyboardActionListener = new KeyboardActionListener(hangmanView, wordService);
		for (final JButton jButton : hangmanView.getKeyboardList()) {
			jButton.addActionListener(keyboardActionListener);
		}
	}
}