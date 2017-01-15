package com.ouassignments.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class HangmanView {

	private JFrame gameBoard;
	private JLabel gameTitle;
	private JLabel wordGuessField;
	private JLabel whiteSpace;
	private JLabel guessAttempts;
	private JButton startStopGameBtn;

	private List<String> alphabetList;
	private List<JButton> keyboardList;
	private String pickedWord;
	private int count;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPickedWord() {
		return pickedWord;
	}

	public void setPickedWord(String pickedWord) {
		this.pickedWord = pickedWord;
	}

	public void initGameboard() {
		setUIManager();
		initializeComponents();
		initializeNorthPanel();
		initializeCenterPanel();
		initializeSouthPanel();
		setGameWindowLocation();
		
		gameBoard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gameBoard.setSize(600, 300);
		gameBoard.pack();
		gameBoard.setVisible(true);
	}

	private void initializeSouthPanel() {
		// South panel will contain start button
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		southPanel.add(startStopGameBtn);
		southPanel.add(guessAttempts);
		gameBoard.add(southPanel, BorderLayout.SOUTH);
	}

	private void initializeCenterPanel() {
		// Center panel will contain keys
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(3, 1));

		JPanel firstRowPanel = new JPanel();
		firstRowPanel.setLayout(new FlowLayout());
		JPanel secondRowPanel = new JPanel();
		firstRowPanel.setLayout(new FlowLayout());
		JPanel thirdRowPanel = new JPanel();
		firstRowPanel.setLayout(new FlowLayout());

		initializeAlphabetList();
		initializeKeyboard(alphabetList);

		for (int i = 0; i < 10; i++) {
			firstRowPanel.add(keyboardList.get(i));

		}
		for (int i = 10; i < 20; i++) {
			secondRowPanel.add(keyboardList.get(i));
		}
		for (int i = 20; i < 26; i++) {
			thirdRowPanel.add(keyboardList.get(i));
		}
		centerPanel.add(firstRowPanel);
		centerPanel.add(secondRowPanel);
		centerPanel.add(thirdRowPanel);

		gameBoard.add(centerPanel, BorderLayout.CENTER);
	}

	private void initializeNorthPanel() {
		// North panel will contain the game title and word guesses field
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2, 3));
		JPanel titlePanel = new JPanel(new FlowLayout());
		gameTitle.setFont(new Font("Sans Serif", Font.BOLD, 16));

		// For title to appear in middle
		titlePanel.add(whiteSpace);
		titlePanel.add(gameTitle);
		titlePanel.add(whiteSpace);

		northPanel.add(titlePanel);

		wordGuessField.setFont(new Font("Sans Serif", Font.PLAIN, 28));
		northPanel.add(wordGuessField);

		// North panel added to game board
		gameBoard.add(northPanel, BorderLayout.NORTH);
	}

	private void initializeComponents() throws HeadlessException {
		gameBoard = new JFrame("Hangman Game");
		gameTitle = new JLabel("Welcome to Hangman Game!");
		wordGuessField = new JLabel("XXXXXXXXXXXXXXX", SwingConstants.CENTER);
		whiteSpace = new JLabel("");
		guessAttempts = new JLabel("0 Tries");
		startStopGameBtn = new JButton("Start Game");
		alphabetList = new ArrayList<String>(26);
		keyboardList = new ArrayList<JButton>(26);
	}

	private void setGameWindowLocation() throws HeadlessException {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		gameBoard.setLocation(dim.width / 3 - gameBoard.getSize().width / 3,
				dim.height / 3 - gameBoard.getSize().height / 3);
	}

	private void setUIManager() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				} else {
					try {
						UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initializeAlphabetList() {
		char alphabet;
		for (alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
			alphabetList.add(Character.toString(alphabet));
		}
	}

	private void initializeKeyboard(List<String> alphabetList) {
		for (int i = 0; i < alphabetList.size(); i++) {
			JButton keyButton = new JButton();
			keyButton.setText(alphabetList.get(i));
			keyButton.setEnabled(false);
			keyboardList.add(keyButton);
			System.out.println();
		}
	}

	public List<JButton> getKeyboardList() {
		return keyboardList;
	}

	public void setKeyboardList(List<JButton> keyboardList) {
		this.keyboardList = keyboardList;
	}

	public JFrame getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(JFrame gameBoard) {
		this.gameBoard = gameBoard;
	}

	public JLabel getWordGuessField() {
		return wordGuessField;
	}

	public void setWordGuessField(JLabel wordGuessField) {
		this.wordGuessField = wordGuessField;
	}

	public JLabel getGuessAttempts() {
		return guessAttempts;
	}

	public void setGuessAttempts(JLabel guessAttempts) {
		this.guessAttempts = guessAttempts;
	}

	public JButton getStartStopGameBtn() {
		return startStopGameBtn;
	}

	public void setStartStopGameBtn(JButton startStopGameBtn) {
		this.startStopGameBtn = startStopGameBtn;
	}
}