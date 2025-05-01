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

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
final class CopyPassphrase
implements ActionListener {
	private MainFrame source_frame;
	
	CopyPassphrase(MainFrame source_frame)
	{
		this.source_frame = source_frame;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		var passphrase = new StringSelection(source_frame.getPassphrase());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(passphrase, null);
	}

}
