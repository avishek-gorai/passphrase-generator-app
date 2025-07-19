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
public class CopyPassphrase
implements ActionListener {
	private App sourceFrame;

	public CopyPassphrase(App source_frame) {
		setSourceFrame(source_frame);
	}
	
	/**
	 * @param sourceFrame the sourceFrame to set
	 */
	private CopyPassphrase setSourceFrame(App sourceFrame) {
		this.sourceFrame = sourceFrame;
		return this;
	}
	
	/**
	 * @return the sourceFrame
	 */
	private App getSourceFrame() {
		return sourceFrame;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		var passphrase = new StringSelection(getSourceFrame().getPassphrase());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(passphrase, null);
	}

}
