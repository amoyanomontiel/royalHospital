package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import views.NewMailView;

public class OpenNewEmailListener {

	private static NewMailView viewNewMail = new NewMailView();
	
	public static void addNewMailOpenListener(JButton openButton) {
		openButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!viewNewMail.isShowing()) {
					viewNewMail.setVisible(true);	
				}
			}
		});
	}
	
}
