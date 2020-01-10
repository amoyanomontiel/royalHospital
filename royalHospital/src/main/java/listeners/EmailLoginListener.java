package listeners;

// all imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.royalhospital.royalHospital.DataModel;

import views.InboxView;
import views.MainMailView;

/**
 * HERE
 * 
 * @author Javier
 * @version 1.0
 */
public class EmailLoginListener {

	private static DataModel dataModelObject = new DataModel();

	/**
	 * Add listener to Button for login email
	 * 
	 * @param loginButton JButton, login button on login View
	 */
	public static void addEmailLoginListener(JButton loginButton) {
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (MainMailView.getTxtUserName().getText().contains(dataModelObject.getKeyMail())) {
					InboxView viewReceiveEmails = new InboxView();
				} else {
					JOptionPane.showMessageDialog(null, dataModelObject.getErrorFormat(),
							dataModelObject.getMessageErrorLogin(), JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}

}
