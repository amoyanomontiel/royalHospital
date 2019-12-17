package Listeners;

// All imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.royalhospital.royalHospital.ThreadRefreshEmails;

/**
 * Class that add the listener of refresh Button
 * 
 * @author Javier
 * @version 1.0
 *
 */
public class RefreshEmail {

	/**
	 * Add refresh button listener
	 * 
	 * @param buttonRefresh JButton, refresh button
	 * @param contentPane JPanel, this JPanel contain all elements of view
	 * @param contentMailPane JPanel, this JPanel contain all elements of emails
	 */
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
