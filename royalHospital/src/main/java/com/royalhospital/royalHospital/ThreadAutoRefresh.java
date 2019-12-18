package com.royalhospital.royalHospital;

// All imports
import javax.swing.JPanel;

import views.InboxView;

/**
 * Class that control the function of autoRefresh email
 * 
 * @author Javier
 * @version 1.0
 *
 */
public class ThreadAutoRefresh extends Thread {

	// Al variables of class
	private JPanel contentView;
	private JPanel contenMailPane;

	/**
	 * Constructor of class
	 * 
	 * @param contentViewParam     JPanel, this JPanel contain the all view elements
	 * @param contentMailPaneParam JPanel, this JPanel contain all elements of
	 *                             emails
	 */
	public ThreadAutoRefresh(JPanel contentViewParam, JPanel contentMailPaneParam) {
		contentView = contentViewParam;
		contenMailPane = contentMailPaneParam;
	}

	/**
	 * Run of Thread
	 */
	public void run() {
		try {
			while (true) {
				sleep(60000);
				synchronized (this) {
					InboxView.setObjectMail(new MailMethods());
					InboxView.getObjectMail().setAllDataConnection("pop.gmail.com", "pop3",
							"jfernandezfernandez.sanjose@alumnado.fundacionloyola.net", "14674858");
					InboxView.getObjectMail().setProperties();
					InboxView.getObjectMail().connectMailServer();
					InboxView.getObjectMail().setFolderEmails();
					InboxView.getObjectMail().receiveAndSaveAllEmails();

					if (MailMethods.getScrollEmails().getItemCount() != MailMethods.getMessages().length + 1) {
						MailMethods.getScrollEmails().removeAllItems();
						MailMethods.updateJComboBox();
						contentView.revalidate();
						contentView.repaint();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error auto updating emails");
		}
	}

}
