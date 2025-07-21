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

	/**
	 * @return the ok_button
	 */
	private JButton getOkButton() {
		return okButton;
	}

	/**
	 * @param ok_button the ok_button to set
	 */
	private ErrorDialog setOkButton(JButton ok_button) {
		this.okButton = ok_button;
		add(getOkButton(), BorderLayout.SOUTH);
		return this;
	}

	/**
	 * @return the errorLabel
	 */
	private JLabel getErrorLabel() {
		return errorLabel;
	}

	/**
	 * @param errorLabel the errorLabel to set
	 */
	private ErrorDialog setErrorLabel(JLabel errorLabel) {
		this.errorLabel = errorLabel;
		add(getErrorLabel(), BorderLayout.CENTER);
		return this;
	}
}
