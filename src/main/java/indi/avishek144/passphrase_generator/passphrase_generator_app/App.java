/* Copyright (C) 2025 Avishek Gorai
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * 
 */

package indi.avishek144.passphrase_generator.passphrase_generator_app;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;

/**
 *
 * @author Avishek Gorai
 */
public class App
extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6523053009929522870L;
	private JLabel passphraseFileNameLabel;
	private JTextArea passphraseViewer;
    private JButton generatePassphraseButton, copyButton, changePassphraseFileButton;
    private JSpinner numberOfWordsSelector;
	private HashMap<Integer, String> wordTable;
	private int numberOfDice;
    
    public App()
    {
        super("Passphrase generator");
        final var MIN_PASSPHRASE_LENGTH = 6;
        
        setPassphraseViewer(new JTextArea());
        setGeneratePassphraseButton(new JButton("Generate"));
        setCopyButton(new JButton("Copy"));
        setPassphraseFileNameLabel(new JLabel());
        setNumberOfWordsSelector(new JSpinner(new SpinnerNumberModel(MIN_PASSPHRASE_LENGTH, MIN_PASSPHRASE_LENGTH, Integer.MAX_VALUE, 1)));
        setChangePassphraseFileButton(new JButton("Change file"));
        
        
        var file_input_container = new Container();
        var main_input_container = new Container();
        var third_row = new Container();
        
        file_input_container.setLayout(new GridLayout(1, 0));
        main_input_container.setLayout(new GridLayout(1, 0));
        third_row.setLayout(new GridLayout(0, 1));
        
        add(file_input_container, BorderLayout.NORTH);
        file_input_container.add(new JLabel("Passphrase File"));
        file_input_container.add(passphraseFileNameLabel);
        file_input_container.add(changePassphraseFileButton);
        
        add(main_input_container, BorderLayout.CENTER);
        main_input_container.add(new JLabel("Number of words"));
        main_input_container.add(numberOfWordsSelector);

        add(third_row, BorderLayout.SOUTH);
        third_row.add(passphraseViewer);
        third_row.add(generatePassphraseButton, BorderLayout.SOUTH);
        third_row.add(copyButton);
        
        try (var file_scanner = new Scanner(getClass().getResourceAsStream("/electronic_frontier_foundation_large_wordlist.txt"))) {
			wordTable = new HashMap<Integer, String>();
			var first_number = file_scanner.next();
			numberOfDice = first_number.length();
			
			wordTable.put(Integer.parseInt(first_number), file_scanner.next());
			while (file_scanner.hasNext()) {
				wordTable.put(file_scanner.nextInt(), file_scanner.next());
			}
		}
        
        setPassphraseFileName("electronic_frontier_foundation_large_wordlist.txt");
        getChangePassphraseFileButton().addActionListener(new ChangePassphraseFile(this));
        getGeneratePassphraseButton().addActionListener(new GeneratePassphrase(this));
        getPassphraseViewer().setLineWrap(true);
        getPassphraseViewer().setEditable(false);
        getCopyButton().addActionListener(new CopyPassphrase(this));
        setResizable(false);
        setSize(800, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
	 * @return the passphraseViewer
	 */
	private JTextArea getPassphraseViewer() {
		return passphraseViewer;
	}

	/**
	 * @return the generatePassphraseButton
	 */
	private JButton getGeneratePassphraseButton() {
		return generatePassphraseButton;
	}

	/**
	 * @return the copyButton
	 */
	private JButton getCopyButton() {
		return copyButton;
	}

	/**
	 * @return the changePassphraseFileButton
	 */
	private JButton getChangePassphraseFileButton() {
		return changePassphraseFileButton;
	}

	/**
	 * @param passphraseFileNameLabel the passphraseFileNameLabel to set
	 */
	private App setPassphraseFileNameLabel(JLabel passphraseFileNameLabel) {
		this.passphraseFileNameLabel = passphraseFileNameLabel;
		return this;
	}

	/**
	 * @param passphraseViewer the passphraseViewer to set
	 */
	private App setPassphraseViewer(JTextArea passphraseViewer) {
		this.passphraseViewer = passphraseViewer;
		return this;
	}

	/**
	 * @param generatePassphraseButton the generatePassphraseButton to set
	 */
	private App setGeneratePassphraseButton(JButton generatePassphraseButton) {
		this.generatePassphraseButton = generatePassphraseButton;
		return this;
	}

	/**
	 * @param copyButton the copyButton to set
	 */
	private App setCopyButton(JButton copyButton) {
		this.copyButton = copyButton;
		return this;
	}

	/**
	 * @param choosePassphraseFileButton the choosePassphraseFileButton to set
	 */
	private App setChangePassphraseFileButton(JButton choosePassphraseFileButton) {
		this.changePassphraseFileButton = choosePassphraseFileButton;
		return this;
	}

	/**
	 * @param numberOfWordsSelector the numberOfWordsSelector to set
	 */
	private App setNumberOfWordsSelector(JSpinner numberOfWordsSelector) {
		this.numberOfWordsSelector = numberOfWordsSelector;
		return this;
	}

	public int getNumberOfWords() 
    {
        var number_of_words = (Number) numberOfWordsSelector.getValue();
        return number_of_words.intValue();
    }
    
    public App setPassphraseFileName(String passphrase_file_name)
    {
        passphraseFileNameLabel.setText(passphrase_file_name);
        return this;
    }

	public App setWordTable(HashMap<Integer, String> word_table) {
		this.wordTable = word_table;
		return this;
	}

	/**
	 * @return the word_table
	 */
	public HashMap<Integer, String> getWordTable()
	{
		return wordTable;
	}

	public int getNumberOfDice()
	{
		return numberOfDice;
	}
	
	public App setNumberOfDice(int n)
	{
		numberOfDice = n;
		return this;
	}

	public App setPassphrase(String passphrase) {
		passphraseViewer.setText(passphrase);
		return this;
	}

	public String getPassphrase() 
	{
		return passphraseViewer.getText();
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() -> new App());
	}
}
