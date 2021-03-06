package com.royalhospital.royalHospital;

import java.sql.Ref;

// All imports
import javax.swing.JPanel;

import listeners.OpenNewEmailListener;
import views.InboxView;
import views.MainMailView;
import views.NewMailView;

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
	private DataModel dataModelObject;

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
		dataModelObject = new DataModel();
	}

	/**
	 * Run of Thread If the views are closed, the thread end
	 */
	public void run() {

		try {

			boolean refresh = true;

			while (refresh) {
				sleep(60000);
				synchronized (this) {
					if (InboxView.getInstance().isShowing()) {
						InboxView.setObjectMail(new MailMethods());
						InboxView.getObjectMail().setAllDataConnection(dataModelObject.getPopGmail(),
								dataModelObject.getPop3(), MainMailView.getTxtUserName().getText(),
								new String(MainMailView.getTxtPassword().getPassword()));
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
					} else {
						refresh = false;
						for (int counter = 0; counter < ListEmailViews.getAllEmailView().size(); counter++) {
							ListEmailViews.getAllEmailView().get(counter).dispose();
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(dataModelObject.getMessageErrorUpdatingEmails());
		}
	}

}
