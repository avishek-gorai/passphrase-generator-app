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
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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
	private final JLabel  passphrase_file_label, number_of_words_label, passphrase_file_name_label, passphrase_viewer;
    private final JButton generate_passphrase_button, copy_button, choose_passphrase_file_button;
    private final JSpinner number_of_words_selector;
    
    private File passphrase_file;
    
    MainFrame()
    {
        super("Passphrase generator");
        final var MIN_PASSPHRASE_LENGTH = 6;
        
        this.passphrase_viewer             = new JLabel();
        this.passphrase_file_label         = new JLabel("Passphrase file");
        this.number_of_words_label         = new JLabel("Number of words");
        this.generate_passphrase_button    = new JButton("Generate");
        this.copy_button                   = new JButton("Copy");
        this.passphrase_file_name_label    = new JLabel();
        this.number_of_words_selector      = new JSpinner(new SpinnerNumberModel(MIN_PASSPHRASE_LENGTH, MIN_PASSPHRASE_LENGTH, Integer.MAX_VALUE, 1));
        this.choose_passphrase_file_button = new JButton("Choose file");
        
        this.choose_passphrase_file_button.addActionListener(new ChoosePassphraseFile(this));
        this.generate_passphrase_button.addActionListener(new GeneratePassphrase(this));
        
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
        
        this.setSize(400, 180);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public int getNumber_of_words() 
    {
        var number_of_words = (Number) this.number_of_words_selector.getValue();
        
        return number_of_words.intValue();
    }
    
    public File getPassphrase_file()
    {
        return this.passphrase_file;
    }
    
    public MainFrame setPassphrase_file(File passphrase_file)
    {
        this.passphrase_file = passphrase_file;
        this.passphrase_file_name_label.setText(passphrase_file.getName());
        
        return this;
    }
}
