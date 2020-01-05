package listeners;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class CheckEmailAddressListener {

	public static void addNewLostFocusListener(JTextField addresseeTextField, JLabel addresseeLabel) {
		addresseeTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (!addresseeTextField.getText().contains(".sanjose@alumnado.fundacionloyola.net")
						&& !addresseeTextField.getText().contains("@gmail.com")) {
					addresseeLabel.setForeground(Color.RED);
				addresseeLabel.setToolTipText("no se ha encontrado el correo '" + addresseeTextField.getText()
						+ "'. Comprueba que no haya errores y vuelve a intentarlo.");
				}
				else {
					addresseeLabel.setForeground(Color.BLACK);
					addresseeLabel.setToolTipText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {}
		});
	}
}
