package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import views.InboxView;
import views.MainMailView;

public class EmailLoginListener {

	
	public static void addEmailLoginListener(JButton loginButton) {
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(MainMailView.getTxtUserName().getText().contains("@alumnado.fundacionloyola.net")) {
					InboxView viewReceiveEmails = new InboxView();
				}
				else {
					JOptionPane.showMessageDialog(null, "Formato de correo inválido", "Login Fallido",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
	
}
