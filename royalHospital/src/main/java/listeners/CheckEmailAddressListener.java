package listeners;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class CheckEmailAddressListener {

	public static void addNewLostFocusListener(JTextField addresseeTextField, JLabel lblWarning) {
		addresseeTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				String addressee = addresseeTextField.getText();
				String[] addressees;
				ArrayList<String> errorList = new ArrayList<String>();
				int occurrences = 0;
				int errorCounter = 0;
				String invalidEmailError = "No se ha encontrado el correo '*'. Comprueba que no haya errores y vuelve a intentarlo";
				String multipleAddressees = "Pon una coma entre las direcciones de correo electrónico para mandar el correo a múltiples personas a la vez";

				while (addressee.indexOf("@") > -1) {
					addressee = addressee.substring(addressee.indexOf("@") + 1, addressee.length());
					occurrences++;
				}

				if (occurrences > 1) {
					if (addresseeTextField.getText().contains(",")) {
						addressees = addresseeTextField.getText().split(",");
					}
					else if (addresseeTextField.getText().contains(" ")) {
						addressees = addresseeTextField.getText().split(" ");
					} else {
						addressees = new String[1];
						addressees[0] = addresseeTextField.getText();
					}
				} else {
					addressees = new String[1];
					addressees[0] = addresseeTextField.getText();
				}

				lblWarning.setVisible(true);
				lblWarning.setEnabled(true);

				for (int i = 0; i < occurrences; i++) {
					if (!addressees[i].equals(" ")) {
						addressees[i] = addressees[i].trim();

						if (!addressees[i].contains(".sanjose@alumnado.fundacionloyola.net")
								&& !addressees[i].contains("@gmail.com")) {
							String invalidEmailErrorModified = invalidEmailError.replace("*", addressees[i]);
							errorList.add(invalidEmailErrorModified);
							errorCounter++;
						}
						if (occurrences > 1 && !addresseeTextField.getText().contains(",") && !errorList.contains(multipleAddressees)) {
							errorList.add(multipleAddressees);
							errorCounter++;
						}
					}
				}

				if (errorCounter == 1) {
					lblWarning.setToolTipText(errorList.get(0));
				} else if (errorCounter >= 2) {
					StringBuilder multipleErrors = new StringBuilder();
					multipleErrors.append("<html>");
					for (String error : errorList) {
						multipleErrors.append(error);
						multipleErrors.append("<br>");
					}
					multipleErrors.append("</html>");
					lblWarning.setToolTipText(multipleErrors.toString());
				} else {
					lblWarning.setVisible(false);
					lblWarning.setEnabled(false);
					lblWarning.setToolTipText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
	}
}
