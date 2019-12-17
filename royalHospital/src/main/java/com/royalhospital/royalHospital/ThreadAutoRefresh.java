package com.royalhospital.royalHospital;

import javax.swing.JPanel;

import views.InboxView;

public class ThreadAutoRefresh extends Thread{

	private JPanel contentView;
	private JPanel contenMailPane;
	
	public ThreadAutoRefresh(JPanel contentViewParam, JPanel contentMailPaneParam) {
		contentView = contentViewParam;
		contenMailPane = contentMailPaneParam; 
	}
	
	public void run() {
		try {
			while(true) {
				sleep(60000);
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
		} catch (Exception e) {
			System.out.println("Error auto updating emails");
		}
	}
	
}
