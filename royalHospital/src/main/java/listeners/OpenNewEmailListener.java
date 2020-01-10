package listeners;

// All imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import views.NewMailView;

/**
 * This class add the listener to button for to open New Mail View
 * 
 * @author Javier
 * @version 1.0
 *
 */
public class OpenNewEmailListener {

	// All variables
	private static NewMailView viewNewMail = null;

	/**
	 * Add listener to JButton for to open new View
	 * 
	 * @param openButton JButton, this button open new View for to send new Mail
	 */
	public static void addNewMailOpenListener(JButton openButton) {
		openButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				viewNewMail = new NewMailView();
				viewNewMail.setVisible(true);
			}
		});
	}

	// All get and set
	public static NewMailView getViewNewMail() {
		return viewNewMail;
	}

	public static void setViewNewMail(NewMailView viewNewMail) {
		OpenNewEmailListener.viewNewMail = viewNewMail;
	}

}
