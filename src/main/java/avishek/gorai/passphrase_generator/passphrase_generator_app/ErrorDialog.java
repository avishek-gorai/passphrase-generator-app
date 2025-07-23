/**
 * 
 */
package avishek.gorai.passphrase_generator.passphrase_generator_app;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * 
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
		if (error_message != null) {
			getErrorLabel().setText(error_message);
		}
		else {
			getErrorLabel().setText("Unknown Error!");
		}
		getOkButton().addActionListener((action) -> setVisible(false));
		setSize(200, 120);
		setVisible(true);
	}

	JButton getOkButton() {
		return okButton;
	}

	ErrorDialog setOkButton(JButton ok_button) {
		this.okButton = ok_button;
		add(getOkButton(), BorderLayout.SOUTH);
		return this;
	}

	JLabel getErrorLabel() {
		return errorLabel;
	}

	ErrorDialog setErrorLabel(JLabel errorLabel) {
		this.errorLabel = errorLabel;
		add(getErrorLabel(), BorderLayout.CENTER);
		return this;
	}
}
