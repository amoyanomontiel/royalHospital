package com.royalhospital.royalHospital;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import Listeners.ScrollEmailListener;
import views.InboxView;

/**
 * Class that control the function of button refresh
 * 
 * @author Javier
 * @version 1.0
 *
 */
public class ThreadRefreshEmails extends Thread{

	// All variables of class
	private JPanel contentView;
	private JPanel contentMailPane;
	
	/**
	 * Constructor of class
	 * 
	 * @param contentViewParam JPanel, this JPanel contain the all view elements
	 * @param contentMailPaneParam JPanel, this JPanel containt all elements of emails
	 */
	public ThreadRefreshEmails(JPanel contentViewParam, JPanel contentMailPaneParam) {
		contentView = contentViewParam;
		contentMailPane = contentMailPaneParam;
	}
	
	/**
	 * Run of Thread
	 */
	public void run() {
		synchronized (this) {
			InboxView.setObjectMail(new MailMethods());
			InboxView.getObjectMail().setAllDataConnection("pop.gmail.com", "pop3", "jfernandezfernandez.sanjose@alumnado.fundacionloyola.net", "14674858");
			InboxView.getObjectMail().setProperties();
			InboxView.getObjectMail().connectMailServer();
			InboxView.getObjectMail().setFolderEmails();
			InboxView.getObjectMail().receiveAndSaveAllEmails();
			if(MailMethods.getScrollEmails().getItemCount() != MailMethods.getMessages().length+1) {
				MailMethods.getScrollEmails().removeAllItems();
				MailMethods.updateJComboBox();
				contentView.revalidate();
				contentView.repaint();
			}	
		}
	}
	
}
