package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import views.NewMailView;

public class OpenNewEmailListener {

	private static NewMailView viewNewMail = null;
	
	public static void addNewMailOpenListener(JButton openButton) {
		openButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				viewNewMail = new NewMailView();
				viewNewMail.setVisible(true);
			}
		});
	}

	public static NewMailView getViewNewMail() {
		return viewNewMail;
	}

	public static void setViewNewMail(NewMailView viewNewMail) {
		OpenNewEmailListener.viewNewMail = viewNewMail;
	}
	
	
	
}
