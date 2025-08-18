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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * 
 * 
 * @author Avishek Gorai
 */
class ChangePassphraseFileButtonActionListener
implements ActionListener {
	private App mainApp;
	private File passphraseFile;
	
	ChangePassphraseFileButtonActionListener(App a) {
		setMainApp(a);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		var fileChooser = new JFileChooser("Choose passphrase file");

        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return true;
            }

            @Override
            public String getDescription() {
                return "Text Files";
            }

        });
        fileChooser.showOpenDialog(getMainApp());
        setPassphraseFile(fileChooser.getSelectedFile());
	}

	File getPassphraseFile() {
		return passphraseFile;
	}

	private ChangePassphraseFileButtonActionListener setPassphraseFile(File passphraseFile) {
		this.passphraseFile = passphraseFile;
		
		return this;
	}

	private App getMainApp() {
		return mainApp;
	}

	private ChangePassphraseFileButtonActionListener setMainApp(App mainApp) {
		this.mainApp = mainApp;
		
		return this;
	}

}
