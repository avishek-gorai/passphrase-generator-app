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
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;

/**
 *
 * @author Avishek Gorai
 */
final class MainFrame
extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6523053009929522870L;
	
	private final JLabel  passphrase_file_label, number_of_words_label, passphrase_file_name_label;
	private final JTextArea passphrase_viewer;
    private final JButton generate_passphrase_button, copy_button, choose_passphrase_file_button;
    private final JSpinner number_of_words_selector;
    
    private File passphrase_file;
	private HashMap<Integer, String> word_table;
	private int number_of_dice;
    
    MainFrame()
    {
        super("Passphrase generator");
        final var MIN_PASSPHRASE_LENGTH = 6;
        
        this.passphrase_viewer             = new JTextArea();
        this.passphrase_file_label         = new JLabel("Passphrase file");
        this.number_of_words_label         = new JLabel("Number of words");
        this.generate_passphrase_button    = new JButton("Generate");
        this.copy_button                   = new JButton("Copy");
        this.passphrase_file_name_label    = new JLabel();
        this.number_of_words_selector      = new JSpinner(new SpinnerNumberModel(MIN_PASSPHRASE_LENGTH, MIN_PASSPHRASE_LENGTH, Integer.MAX_VALUE, 1));
        this.choose_passphrase_file_button = new JButton("Choose file");
        
        this.choose_passphrase_file_button.addActionListener(new ChoosePassphraseFile(this));
        this.generate_passphrase_button.addActionListener(new GeneratePassphrase(this));
        this.passphrase_viewer.setLineWrap(true);
        this.passphrase_viewer.setEditable(false);
        this.copy_button.addActionListener(new CopyPassphrase(this));
        
        // First row
        var file_input_container = new Container();
        file_input_container.setLayout(new GridLayout(1, 0));
        this.add(file_input_container, BorderLayout.NORTH);
        file_input_container.add(this.passphrase_file_label);
        
        file_input_container.add(this.passphrase_file_name_label);
        file_input_container.add(this.choose_passphrase_file_button);
        
        // Second row
        var main_input_container = new Container();
        main_input_container.setLayout(new GridLayout(1, 0));
        this.add(main_input_container, BorderLayout.CENTER);
        
        main_input_container.add(this.number_of_words_label);
        main_input_container.add(this.number_of_words_selector);

        
        // Third row
        var third_row = new Container();
        third_row.setLayout(new GridLayout(0, 1));
        this.add(third_row, BorderLayout.SOUTH);

        third_row.add(this.passphrase_viewer);
        third_row.add(this.generate_passphrase_button, BorderLayout.SOUTH);
        third_row.add(this.copy_button);
        
        // Loading initial passphrase file
        try (var file_scanner = new Scanner(this.getClass().getResourceAsStream("/electronic_frontier_foundation_large_wordlist.txt"))) {
			this.word_table = new HashMap<Integer, String>();
			var first_number = file_scanner.next();
			this.number_of_dice = first_number.length();
			
			this.word_table.put(Integer.parseInt(first_number), file_scanner.next());
			while (file_scanner.hasNext()) {
				this.word_table.put(file_scanner.nextInt(), file_scanner.next());
			}
		}
        
        this.setPassphraseFileName("electronic_frontier_foundation_large_wordlist.txt");
        
        this.setResizable(false);
        this.setSize(800, 180);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public int getNumberOfWords() 
    {
        var number_of_words = (Number) this.number_of_words_selector.getValue();
        
        return number_of_words.intValue();
    }
    
    public MainFrame setPassphraseFileName(String passphrase_file_name)
    {
        this.passphrase_file_name_label.setText(passphrase_file_name);
        
        return this;
    }

	public MainFrame setWordTable(HashMap<Integer, String> word_table) {
		this.word_table = word_table;
		return this;
	}

	/**
	 * @return the word_table
	 */
	public HashMap<Integer, String> getWordTable()
	{
		return word_table;
	}

	public int getNumberOfDice()
	{
		return this.number_of_dice;
	}
	
	public MainFrame setNumberOfDice(int n)
	{
		this.number_of_dice = n;
		return this;
	}

	public MainFrame setPassphrase(String passphrase) {
		this.passphrase_viewer.setText(passphrase);
		return this;
	}

	public String getPassphrase() 
	{
		return this.passphrase_viewer.getText();
	}
}
