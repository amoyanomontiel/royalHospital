/**
 * This class is responsible for adding a focus event to the addressees textfield and verifying 
 * that the email addresses are from gmail or the foundation and if not, send a warning
 * @author Fernando Cañadas Ortega
 * @version 1.0
 * Realizado el 9 november 2020
 */

package listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.royalhospital.royalHospital.DataModel;

public class CheckEmailAddressListener {
	private DataModel model = new DataModel(); 

	/**
	 * Method that adds a focus event to the addressees textfield and when this textfield loses focus, written addressees will be checked
	 * 
	 * @param addresseeTextField Textfield that will contains the addressees that the message is aimed at.
	 * @param lblWarning         Label that will contain a error icon and a tooltip
	 *                           text with the errors.
	 */
	public static void addNewLostFocusListener(JTextField addresseeTextField, JLabel lblWarning) {
		addresseeTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				String addressee = addresseeTextField.getText();
				String[] addressees;
				ArrayList<String> errorList = new ArrayList<String>();
				int occurrences = 0;
				int errorCounter = 0;
				String invalidEmailError = "La dirección de correo '*' no pertenece a gmail ni a la fundación loyola.";
				String multipleAddressees = "Pon una coma entre las direcciones de correo electrónico para mandar el correo a múltiples personas a la vez";
				String excessSpace = "No puedes dejar espacios entre las direcciones de correo electrónico y las comas separadoras";

				while (addressee.indexOf("@") > -1) {
					addressee = addressee.substring(addressee.indexOf("@") + 1, addressee.length());
					occurrences++;
				}

				if (occurrences > 1) {
					if (addresseeTextField.getText().contains(",")) {
						addressees = addresseeTextField.getText().split(",");
					} else if (addresseeTextField.getText().contains(" ")) {
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
						String trimmedAddresse = addressees[i].trim();

						if (!trimmedAddresse.equals(addressees[i])) {
							errorList.add(excessSpace);
							errorCounter++;
						}

						if (!addressees[i].contains(".sanjose@alumnado.fundacionloyola.net")
								&& !addressees[i].contains("@gmail.com")) {
							String invalidEmailErrorModified = invalidEmailError.replace("*", addressees[i]);
							errorList.add(invalidEmailErrorModified);
							errorCounter++;
						}
						if (occurrences > 1 && !addresseeTextField.getText().contains(",")
								&& !errorList.contains(multipleAddressees) && !errorList.contains(excessSpace)) {
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
