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
import javax.swing.JFileChooser;

/**
 *
 * @author Avishek Gorai
 */
final class ChoosePassphraseFile
implements ActionListener {
    private final MainFrame source_frame;
    
    ChoosePassphraseFile(MainFrame source_frame)
    {
        this.source_frame = source_frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        var file_chooser = new JFileChooser("Choose passphrase file");
        
        file_chooser.setFileFilter(new TextFileFilter());
        file_chooser.showOpenDialog(source_frame);
        
        var selected_file = file_chooser.getSelectedFile();
        if (selected_file != null) {
        	source_frame.setPassphrase_file(selected_file);
        }
    }
}
