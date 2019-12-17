package com.royalhospital.royalHospital;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import Listeners.ScrollEmailListener;
import views.InboxView;

public class ThreadRefreshEmails extends Thread{

	private JPanel contentView;
	private JPanel contentMailPane;
	
	public ThreadRefreshEmails(JPanel contentViewParam, JPanel contentMailPaneParam) {
		contentView = contentViewParam;
		contentMailPane = contentMailPaneParam;
	}
	
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
