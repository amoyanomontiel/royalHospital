package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import views.InboxView;
import views.MainMailView;

public class EmailLoginListener {

	
	public static void addEmailLoginListener(JButton loginButton) {
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(MainMailView.getTxtUserName().getText().contains("@alumnado.fundacionloyola.net")) {
					MainMailView.getFrame().setVisible(false);;
					InboxView viewReceiveEmails = new InboxView();
				}
			}
		});
	}
	
}
