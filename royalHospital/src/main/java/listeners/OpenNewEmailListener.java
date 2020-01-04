package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import views.NewMailView;

public class OpenNewEmailListener {

	
	public static void addNewMailOpenListener(JButton openButton) {
		openButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				NewMailView viewNewMail = new NewMailView();
				viewNewMail.setVisible(true);
			}
		});
	}
	
}
