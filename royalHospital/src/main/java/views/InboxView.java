package views;

import java.awt.BorderLayout;
// All imports
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.royalhospital.royalHospital.MailMethods;
import com.royalhospital.royalHospital.ThreadAutoRefresh;

import Listeners.RefreshEmail;

public class InboxView extends JFrame {

	// Al variables of class

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final int width = 30;
	private final int height = 30;
	private JPanel messagePanel;
	private JPanel headPane;
	private static JPanel contextMailPane;
	private static MailMethods objectMail;
	private static JPanel mailListPane;
	private static ThreadAutoRefresh objectThreadAutoRefresh;

	/**
	 * Launch the View
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					objectMail = new MailMethods();
					objectMail.setAllDataConnection("pop.gmail.com", "pop3",
							"jfernandezfernandez.sanjose@alumnado.fundacionloyola.net", "14674858");
					objectMail.setProperties();
					objectMail.connectMailServer();
					objectMail.setFolderEmails();
					objectMail.receiveAndSaveAllEmails();
					InboxView frame = new InboxView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InboxView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 748);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		headPane = new JPanel();
		contentPane.add(headPane, BorderLayout.NORTH);

		JLabel lblInbox = new JLabel("Buzón de entrada");
		lblInbox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		headPane.add(lblInbox);

		JButton btnRefresh = new JButton("Refrescar");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		headPane.add(btnRefresh);

		JButton btnWriteEmail = new JButton("Redactar");
		btnWriteEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		headPane.add(btnWriteEmail);

		JButton btnCloseInbox = new JButton("Volver");
		btnCloseInbox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		headPane.add(btnCloseInbox);

		contextMailPane = new JPanel();
		contentPane.add(contextMailPane, BorderLayout.SOUTH);

		mailListPane = new JPanel();

		/*
		 * Generate 2 JPanels: 1. JPanel = JPanel with JComboBox with all emails 2.
		 * JPanel = JPanel that show the context of message
		 */
		//mailListPane = objectMail.generateJComboBoxWithEmails(contextMailPane, contentPane);

		/**
		 * Add listener to the Refresh button
		 */
		RefreshEmail.addRefreshButtonListener(btnRefresh, contentPane, contextMailPane);
		contentPane.add(mailListPane);

		/**
		 * Create and start Thread that auto refresh the JComboBox with emails
		 */
		objectThreadAutoRefresh = new ThreadAutoRefresh(contentPane, contextMailPane);
		objectThreadAutoRefresh.start();
	}

	// All get and set

	public static MailMethods getObjectMail() {
		return objectMail;
	}

	public static void setObjectMail(MailMethods objectMail) {
		InboxView.objectMail = objectMail;
	}

	public static JPanel getContextMailPane() {
		return contextMailPane;
	}

	public static void setContextMailPane(JPanel contextMailPane) {
		InboxView.contextMailPane = contextMailPane;
	}

	public static JPanel getMailListPane() {
		return mailListPane;
	}

	public static void setMailListPane(JPanel mailListPane) {
		InboxView.mailListPane = mailListPane;
	}

}