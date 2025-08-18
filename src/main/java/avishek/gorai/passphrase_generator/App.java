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

package avishek.gorai.passphrase_generator;

import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.swing.filechooser.FileFilter;
import javax.swing.*;

/**
 * I represent the Passphrase Generator application.
 *
 * @author Avishek Gorai
 */
class App 
extends JFrame {
	private static final long serialVersionUID = 6523053009929522870L;
	private static final int minimumPassphraseLength = 6, appFrameHeight = 180, appFrameWidth = 900;
	private JLabel passphraseFileNameLabel;
	private JTextArea passphraseViewer;
    private JButton generatePassphraseButton, copyButton, changePassphraseFileButton;
    private JSpinner numberOfWordsSelector;
    private HashMap<Integer, String> wordTable;
    private File passphraseFile;
    private int numberOfDice;

    App() {
        super("Passphrase generator");
        setLayout(new GridBagLayout());
        setWordTable(new HashMap<Integer, String>());
        setPassphraseViewer(new JTextArea());
        setGeneratePassphraseButton(new JButton("Generate"));
        setCopyButton(new JButton("Copy"));
        setPassphraseFileNameLabel(new JLabel());
        setNumberOfWordsSelector(new JSpinner(new SpinnerNumberModel(App.getMinimumpassphraselength(), App.getMinimumpassphraselength(), Integer.MAX_VALUE, 1)));
        setChangePassphraseFileButton(new JButton("Change file"));
        setPassphraseFile(getClass().getClassLoader().getResource("electronic_frontier_foundation_large_wordlist.txt").getPath());
        getPassphraseViewer().setLineWrap(true);
        getPassphraseViewer().setEditable(false);
        add(new JLabel("Passphrase File"), new AppLayoutConstraint().setGridX(0).setGridY(0));
        add(getPassphraseFileNameLabel(), new AppLayoutConstraint().setGridX(1).setGridY(0));
        add(getChangePassphraseFileButton(), new AppLayoutConstraint().setGridX(2).setGridY(0));
        add(new JLabel("Number of words"), new AppLayoutConstraint().setGridX(0).setGridY(1));
        add(getNumberOfWordsSelector(), new AppLayoutConstraint().setGridX(1).setGridY(1).setGridWidth(2));
        add(getPassphraseViewer(), new AppLayoutConstraint().setGridX(0).setGridY(2).setGridWidth(3));
        add(getCopyButton(), new AppLayoutConstraint().setGridX(0).setGridY(3).setGridWidth(3));
        add(getGeneratePassphraseButton(), new AppLayoutConstraint().setGridX(0).setGridY(4).setGridWidth(3));
        setResizable(false);
        setSize(App.getAppframewidth(), App.getAppframeheight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }

    private static int getMinimumpassphraselength() {
		return minimumPassphraseLength;
	}

    private App setPassphraseFile(String path) {
        setPassphraseFile(new File(path));
        
        return this;
    }

    private File getPassphraseFile() {
        return passphraseFile;
    }

    private App setPassphraseFile(File passphraseFile) {
        try (var fileScanner = new Scanner(passphraseFile)) {
            var firstNumber = fileScanner.next();
            setNumberOfDice(firstNumber.length());

            getWordTable().put(Integer.parseInt(firstNumber), fileScanner.next());
            while (fileScanner.hasNext()) {
                getWordTable().put(fileScanner.nextInt(), fileScanner.next());
            }
            getPassphraseFileNameLabel().setText(passphraseFile.getName());
        }
        catch (FileNotFoundException e1) {
            new ErrorDialog(this, e1.getMessage());
        }
        
        this.passphraseFile = passphraseFile;
        
        return this;
    }

    private JTextArea getPassphraseViewer() {
        return passphraseViewer;
    }

    private JButton getGeneratePassphraseButton() {
        return generatePassphraseButton;
    }

    private JButton getCopyButton() {
        return copyButton;
    }

    private JButton getChangePassphraseFileButton() {
        return changePassphraseFileButton;
    }

    private App setPassphraseFileNameLabel(JLabel passphraseFileNameLabel) {
        this.passphraseFileNameLabel = passphraseFileNameLabel;
       
        return this;
    }

    private JLabel getPassphraseFileNameLabel() {
        return passphraseFileNameLabel;
    }

    private App setPassphraseViewer(JTextArea passphraseViewer) {
        this.passphraseViewer = passphraseViewer;
        
        return this;
    }

    private App setGeneratePassphraseButton(JButton generatePassphraseButton) {
        generatePassphraseButton.addActionListener((action) -> {
            var randomGenerator = new Random();
            var passphrase = new StringBuilder();

            for (var index = 1; index <= getNumberOfWords(); ++index) {
                var number = 0;
                for (var j = 1; j <= getNumberOfDice(); ++j) {
                    number = number * 10 + randomGenerator.nextInt(5) + 1;
                }

                passphrase.append(getWordTable().get(number)).append(' ');
            }

            passphrase.deleteCharAt(passphrase.length() - 1);
            getPassphraseViewer().setText(passphrase.toString());
        });
        
        this.generatePassphraseButton = generatePassphraseButton;
        
        return this;
    }

    private App setCopyButton(JButton copyButton) {
        copyButton.addActionListener((action) -> Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(getPassphraseViewer().getText()), null));
        this.copyButton = copyButton;
       
        return this;
    }

    private App setChangePassphraseFileButton(JButton changePassphraseFileButton) {
    	var actionListener = new ChangePassphraseFileButtonActionListener(this);
    	changePassphraseFileButton.addActionListener(actionListener);
    	if (actionListener.getPassphraseFile() != null) {
    		setPassphraseFile(actionListener.getPassphraseFile());
    	}
       
        this.changePassphraseFileButton = changePassphraseFileButton;
        
        return this;
    }

    private App setNumberOfWordsSelector(JSpinner numberOfWordsSelector) {
        this.numberOfWordsSelector = numberOfWordsSelector;
       
        return this;
    }

    private int getNumberOfWords() {
        var number_of_words = (Number) getNumberOfWordsSelector().getValue();
       
        return number_of_words.intValue();
    }

    private JSpinner getNumberOfWordsSelector() {
        return numberOfWordsSelector;
    }

    private App setWordTable(HashMap<Integer, String> word_table) {
        this.wordTable = word_table;
        
        return this;
    }

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

    private static int getAppframewidth() {
        return appFrameWidth;
    }

    private static int getAppframeheight() {
        return appFrameHeight;
    }
}
