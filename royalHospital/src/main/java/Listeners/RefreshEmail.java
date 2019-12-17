package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.royalhospital.royalHospital.ThreadRefreshEmails;

public class RefreshEmail {

	public static void addRefreshButtonListener(JButton buttonRefresh, JPanel contentPane, JPanel contentMailPane) {
		buttonRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ThreadRefreshEmails objectThread = new ThreadRefreshEmails(contentPane, contentMailPane);
				objectThread.start();
			}
		});
	}
	
}
