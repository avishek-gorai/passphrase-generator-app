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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
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
 *
 * @author Avishek Gorai
 */
public class App extends JFrame {
	private static final long serialVersionUID = 6523053009929522870L;
	private static final int minimumPassphraseLength = 6;
	private JLabel passphraseFileNameLabel;
	private JTextArea passphraseViewer;
    private JButton generatePassphraseButton, copyButton, changePassphraseFileButton;
    private JSpinner numberOfWordsSelector;
    private HashMap<Integer, String> wordTable;
    private File passphraseFile;
    private int numberOfDice;
    private Container fileInputContainer, mainInputContainer, thirdRowContainer;

    App() {
        super("Passphrase generator");
        setWordTable(new HashMap<Integer, String>());
        setPassphraseViewer(new JTextArea());
        setGeneratePassphraseButton(new JButton("Generate"));
        setCopyButton(new JButton("Copy"));
        setPassphraseFileNameLabel(new JLabel());
        setNumberOfWordsSelector(new JSpinner(new SpinnerNumberModel(App.getMinimumpassphraselength(), App.getMinimumpassphraselength(), Integer.MAX_VALUE, 1)));
        setChangePassphraseFileButton(new JButton("Change file"));
        setFileInputContainer(new Container());
        setMainInputContainer(new Container());
        setThirdRowContainer(new Container());
        setPassphraseFile(getClass().getClassLoader().getResource("electronic_frontier_foundation_large_wordlist.txt").getPath());
        getFileInputContainer().add(new JLabel("Passphrase File"));
        getFileInputContainer().add(getPassphraseFileNameLabel());
        getFileInputContainer().add(getChangePassphraseFileButton());
        add(getFileInputContainer(), BorderLayout.NORTH);
        getMainInputContainer().add(new JLabel("Number of words"));
        getMainInputContainer().add(getNumberOfWordsSelector());
        add(getMainInputContainer(), BorderLayout.CENTER);
        getThirdRowContainer().add(getPassphraseViewer());
        getPassphraseViewer().setLineWrap(true);
        getPassphraseViewer().setEditable(false);
        getThirdRowContainer().add(getGeneratePassphraseButton(), BorderLayout.SOUTH);
        getThirdRowContainer().add(getCopyButton());
        add(getThirdRowContainer(), BorderLayout.SOUTH);
        setResizable(false);
        setSize(900, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

	static int getMinimumpassphraselength() {
		return minimumPassphraseLength;
	}

    Container getFileInputContainer() {
        return fileInputContainer;
    }

    App setFileInputContainer(Container fileInputContainer) {
        fileInputContainer.setLayout(new GridLayout(1, 0));
        this.fileInputContainer = fileInputContainer;
        return this;
    }

    Container getMainInputContainer() {
        return mainInputContainer;
    }

    App setMainInputContainer(Container mainInputContainer) {
        mainInputContainer.setLayout(new GridLayout(1, 0));
        this.mainInputContainer = mainInputContainer;
        return this;
    }

    Container getThirdRowContainer() {
        return thirdRowContainer;
    }

    App setThirdRowContainer(Container thirdRowContainer) {
        thirdRowContainer.setLayout(new GridLayout(0, 1));
        this.thirdRowContainer = thirdRowContainer;
        return this;
    }

    App setPassphraseFile(String path) {
        setPassphraseFile(new File(path));
        return this;
    }

    File getPassphraseFile() {
        return passphraseFile;
    }

    App setPassphraseFile(File passphraseFile) {
        this.passphraseFile = passphraseFile;

        try (var file_scanner = new Scanner(getPassphraseFile())) {
            var first_number = file_scanner.next();
            setNumberOfDice(first_number.length());

            getWordTable().put(Integer.parseInt(first_number), file_scanner.next());
            while (file_scanner.hasNext()) {
                getWordTable().put(file_scanner.nextInt(), file_scanner.next());
            }
            getPassphraseFileNameLabel().setText(getPassphraseFile().getName());
        } catch (Exception e1) {
            new ErrorDialog(this, e1.getMessage());
        }
        return this;
    }

    JTextArea getPassphraseViewer() {
        return passphraseViewer;
    }

    JButton getGeneratePassphraseButton() {
        return generatePassphraseButton;
    }

    JButton getCopyButton() {
        return copyButton;
    }

    JButton getChangePassphraseFileButton() {
        return changePassphraseFileButton;
    }

    App setPassphraseFileNameLabel(JLabel passphraseFileNameLabel) {
        this.passphraseFileNameLabel = passphraseFileNameLabel;
        return this;
    }

    JLabel getPassphraseFileNameLabel() {
        return passphraseFileNameLabel;
    }

    App setPassphraseViewer(JTextArea passphraseViewer) {
        this.passphraseViewer = passphraseViewer;
        return this;
    }

    App setGeneratePassphraseButton(JButton generatePassphraseButton) {
        generatePassphraseButton.addActionListener((action) -> {
            var random_generator = new Random();
            var passphrase = new StringBuilder();

            for (var index = 1; index <= getNumberOfWords(); ++index) {
                var number = 0;
                for (var j = 1; j <= getNumberOfDice(); ++j) {
                    number = number * 10 + random_generator.nextInt(5) + 1;
                }

                passphrase.append(getWordTable().get(number)).append(' ');
            }

            passphrase.deleteCharAt(passphrase.length() - 1);
            getPassphraseViewer().setText(passphrase.toString());
        });
        this.generatePassphraseButton = generatePassphraseButton;
        return this;
    }

    App setCopyButton(JButton copyButton) {
        copyButton.addActionListener((action) -> Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(getPassphraseViewer().getText()), null));
        this.copyButton = copyButton;
        return this;
    }

    App setChangePassphraseFileButton(JButton changePassphraseFileButton) {
        changePassphraseFileButton.addActionListener((action) -> {
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
        this.changePassphraseFileButton = changePassphraseFileButton;
        return this;
    }

    App setNumberOfWordsSelector(JSpinner numberOfWordsSelector) {
        this.numberOfWordsSelector = numberOfWordsSelector;
        return this;
    }

    int getNumberOfWords() {
        var number_of_words = (Number) getNumberOfWordsSelector().getValue();
        return number_of_words.intValue();
    }

    JSpinner getNumberOfWordsSelector() {
        return numberOfWordsSelector;
    }

    App setWordTable(HashMap<Integer, String> word_table) {
        this.wordTable = word_table;
        return this;
    }

    HashMap<Integer, String> getWordTable() {
        return wordTable;
    }

    int getNumberOfDice() {
        return numberOfDice;
    }

    App setNumberOfDice(int n) {
        numberOfDice = n;
        return this;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}
