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

package avishek.gorai.passphrase_generator.passphrase_generator_app;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.JTextArea;

/**
 * The main class.
 * @author Avishek Gorai
 */
public class App
extends JFrame {
    /**
	 * The Serial version UID.
	 */
	private static final long serialVersionUID = 6523053009929522870L;
	private JLabel passphraseFileNameLabel;
	private JTextArea passphraseViewer;
    private JButton generatePassphraseButton, copyButton, changePassphraseFileButton;
    private JSpinner numberOfWordsSelector;
	private HashMap<Integer, String> wordTable;
	private File passphraseFile;
	private int numberOfDice;
    
    App() {
        super("Passphrase generator");
        final var MIN_PASSPHRASE_LENGTH = 6;
        
        setWordTable(new HashMap<Integer, String>());
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
        
        setPassphraseFile(getClass().getClassLoader().getResource("electronic_frontier_foundation_large_wordlist.txt").getPath());
        getChangePassphraseFileButton().addActionListener((action) -> {
        	var file_chooser = new JFileChooser("Choose passphrase file");
            
            file_chooser.setFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return true;
				}

				@Override
				public String getDescription() {
					return "Text Files";
				}
            	
            });
            file_chooser.showOpenDialog(this);
            
            var selected_file = file_chooser.getSelectedFile();
            if (selected_file != null) {
            	setPassphraseFile(selected_file);
            }
        });
        getGeneratePassphraseButton().addActionListener((action) -> {
             var random_generator = new Random();
             var passphrase = new StringBuilder();
             
             for (var index = 1; index <= getNumberOfWords(); ++index) {
     	        var number = 0;
     	        for (var j = 1; j <= getNumberOfDice(); ++j) {
     	        	number = number * 10 + random_generator.nextInt(5) + 1;
     	        }
     	        
     	        passphrase.append(getWordTable().get(number)).append(' ');
             }
             
             passphrase.deleteCharAt(passphrase.length()-1);
             getPassphraseViewer().setText(passphrase.toString());
        });
        getPassphraseViewer().setLineWrap(true);
        getPassphraseViewer().setEditable(false);
        getCopyButton().addActionListener((action) -> Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(getPassphraseViewer().getText()), null));
        setResizable(false);
        setSize(800, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private App setPassphraseFile(String path) {
		setPassphraseFile(new File(path));
		return this;
	}

	/**
	 * @return the passphraseFile
	 */
	private File getPassphraseFile() {
		return passphraseFile;
	}

	/**
	 * @param passphraseFile The passphrase file to set.
	 */
	private App setPassphraseFile(File passphraseFile) {
		this.passphraseFile = passphraseFile;
		
		try (var file_scanner = new Scanner(getPassphraseFile())) {
    		var first_number = file_scanner.next();
			setNumberOfDice(first_number.length());
			
			getWordTable().put(Integer.parseInt(first_number), file_scanner.next());
			while (file_scanner.hasNext()) {
				getWordTable().put(file_scanner.nextInt(), file_scanner.next());
			}
			getPassphraseFileNameLabel().setText(getPassphraseFile().getName());
		} catch (FileNotFoundException e1) {
			new ErrorDialog(this, e1.getMessage());
		}
		return this;
	}

	/**
     * Returns the passphraseViewer.
	 * @return The passphraseViewer.
	 */
	private JTextArea getPassphraseViewer() {
		return passphraseViewer;
	}

	/**
	 * Returns the generatePassphraseButton.
	 * @return The generatePassphraseButton
	 */
	private JButton getGeneratePassphraseButton() {
		return generatePassphraseButton;
	}

	/**
	 * @return The copyButton
	 */
	private JButton getCopyButton() {
		return copyButton;
	}

	/**
	 * @return The changePassphraseFileButton.
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
	 * @return the passphraseFileNameLabel
	 */
	private JLabel getPassphraseFileNameLabel() {
		return passphraseFileNameLabel;
	}

	/**
	 * @param passphraseViewer The passphraseViewer to set.
	 * @return this.
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
	 * @param copyButton The copyButton to set.
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

	private int getNumberOfWords() 
    {
        var number_of_words = (Number) getNumberOfWordsSelector().getValue();
        return number_of_words.intValue();
    }
    
    /**
	 * @return the numberOfWordsSelector
	 */
	private JSpinner getNumberOfWordsSelector() {
		return numberOfWordsSelector;
	}

	private App setWordTable(HashMap<Integer, String> word_table) {
		this.wordTable = word_table;
		return this;
	}

	/**
	 * @return The wordTable.
	 */
	private HashMap<Integer, String> getWordTable() {
		return wordTable;
	}

	private int getNumberOfDice() {
		return numberOfDice;
	}
	
	private App setNumberOfDice(int n) {
		numberOfDice = n;
		return this;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new App());
	}
}
