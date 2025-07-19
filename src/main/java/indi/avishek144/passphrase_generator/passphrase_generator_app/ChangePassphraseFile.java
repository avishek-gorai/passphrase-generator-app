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

package indi.avishek144.passphrase_generator.passphrase_generator_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 *
 * @author Avishek Gorai
 */
public class ChangePassphraseFile
implements ActionListener {
    private App sourceFrame;

	public ChangePassphraseFile(App source_frame)
    {
        setSourceFrame(source_frame);
    }
	
    /**
	 * @return the source_frame
	 */
	private App getSourceFrame() {
		return sourceFrame;
	}

	/**
	 * @param source_frame the source_frame to set
	 */
	private ChangePassphraseFile setSourceFrame(App source_frame) {
		this.sourceFrame = source_frame;
		return this;
	}
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        var file_chooser = new JFileChooser("Choose passphrase file");
        
        file_chooser.setFileFilter(new TextFileFilter());
        file_chooser.showOpenDialog(getSourceFrame());
        
        var selected_file = file_chooser.getSelectedFile();
        if (selected_file != null) {
        	try (var file_scanner = new Scanner(selected_file)) {
        		var word_table = new HashMap<Integer, String>();
        		var first_number = file_scanner.next();
				var number_of_dice = first_number.length();
				getSourceFrame().setNumberOfDice(number_of_dice);
				word_table.put(Integer.parseInt(first_number), file_scanner.next());
				
				while (file_scanner.hasNext()) {
					word_table.put(file_scanner.nextInt(), file_scanner.next());
				}
				getSourceFrame().setPassphraseFileName(selected_file.getName());
				getSourceFrame().setWordTable(word_table);
			} catch (FileNotFoundException e1) {
				
			}
        }
    }
}
