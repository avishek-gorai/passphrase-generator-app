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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * I represent a dialog box used to show errors.
 * 
 * @author Avishek Gorai
 */
class ErrorDialog
extends JDialog {
	private static final long serialVersionUID = -3105604605372914878L;
	private JLabel errorLabel;
	private JButton okButton;

	ErrorDialog(App main_frame, String error_message) {
		super(main_frame);
		setTitle("Error");
		setErrorLabel(new JLabel());
		setOkButton(new JButton("OK"));
		add(getOkButton(), BorderLayout.SOUTH);
		add(getErrorLabel(), BorderLayout.CENTER);
		if (error_message != null) {
			getErrorLabel().setText(error_message);
		}
		else {
			getErrorLabel().setText("Unknown Error!");
		}
		Thread.dumpStack();
		getOkButton().addActionListener((action) -> setVisible(false));
		setSize(200, 120);
		setVisible(true);
	}

	private JButton getOkButton() {
		return okButton;
	}

	private ErrorDialog setOkButton(JButton ok_button) {
		this.okButton = ok_button;
		
		return this;
	}

	private JLabel getErrorLabel() {
		return errorLabel;
	}

	private ErrorDialog setErrorLabel(JLabel errorLabel) {
		this.errorLabel = errorLabel;
		
		return this;
	}
}
