package com.royalhospital.royalHospital;

import javax.swing.JPanel;

import views.InboxView;
import views.MainMailView;

/**
 * Class that control the function of button refresh
 * 
 * @author Javier
 * @version 1.0
 *
 */
public class ThreadRefreshEmails extends Thread {

	// All variables of class
	private JPanel contentView;
	private JPanel contentMailPane;
	private DataModel dataModelObject;

	/**
	 * Constructor of class
	 * 
	 * @param contentViewParam     JPanel, this JPanel contain the all view elements
	 * @param contentMailPaneParam JPanel, this JPanel containt all elements of
	 *                             emails
	 */
	public ThreadRefreshEmails(JPanel contentViewParam, JPanel contentMailPaneParam) {
		contentView = contentViewParam;
		contentMailPane = contentMailPaneParam;
		dataModelObject = new DataModel();
	}

	/**
	 * Run of Thread
	 */
	public void run() {
		synchronized (this) {
			InboxView.setObjectMail(new MailMethods());
			InboxView.getObjectMail().setAllDataConnection(dataModelObject.getPopGmail(), dataModelObject.getPop3(),
					MainMailView.getTxtUserName().getText(), new String(MainMailView.getTxtPassword().getPassword()));
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

}
