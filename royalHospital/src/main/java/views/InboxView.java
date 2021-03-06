package views;

import java.awt.BorderLayout;
// All imports
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.royalhospital.royalHospital.ListEmailViews;
import com.royalhospital.royalHospital.MailMethods;
import com.royalhospital.royalHospital.ThreadAutoRefresh;

import listeners.OpenNewEmailListener;
import listeners.RefreshEmail;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	private static boolean connectMail;
	private static JFrame instance = null;

	/**
	 * Create the frame.
	 */
	public InboxView() {

		// Save instance of JFrame
		instance = this;
		ListEmailViews.getAllEmailView().add(instance);

		/**
		 * Add listener to close windows
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					if (!NewMailView.getInstance().isShowing()) {
						NewMailView.getInstance().dispose();
						MainMailView.getFrame().dispose();
						setVisible(false);
					}
				} catch (Exception e2) {
					MainMailView.getFrame().dispose();
					setVisible(false);
				}
			}
		});

		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {

					objectMail = new MailMethods();

					String valorPass = new String(MainMailView.getTxtPassword().getPassword());
					objectMail.setAllDataConnection("pop.gmail.com", "pop3", MainMailView.getTxtUserName().getText(),
							valorPass);
					objectMail.setProperties();
					connectMail = objectMail.connectMailServer();
					if (connectMail) {
						MainMailView.getFrame().setVisible(false);
						objectMail.setFolderEmails();
						objectMail.receiveAndSaveAllEmails();

						setResizable(false);
						setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
						setBounds(100, 100, 1381, 551);
						contentPane = new JPanel();
						contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
						setContentPane(contentPane);

						headPane = new JPanel();
						contentPane.add(headPane, BorderLayout.NORTH);

						JLabel lblInbox = new JLabel("Buzón de entrada");
						lblInbox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
						headPane.add(lblInbox);

						JButton btnRefresh = new JButton("");
						btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 15));
						btnRefresh.setToolTipText("Refrescar");
						ImageIcon refreshIcon = new ImageIcon("src//main//java//views//refresh.png");
						Icon newRefreshIcon = new ImageIcon(
								refreshIcon.getImage().getScaledInstance(width + 5, height, Image.SCALE_DEFAULT));
						btnRefresh.setIcon(newRefreshIcon);
						headPane.add(btnRefresh);

						JButton btnWriteEmail = new JButton("Redactar");
						btnWriteEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
						ImageIcon mailIcon = new ImageIcon("src//main//java//views//newGmail.png");
						Icon newMailIcon = new ImageIcon(
								mailIcon.getImage().getScaledInstance(width + 15, height, Image.SCALE_DEFAULT));
						btnWriteEmail.setIcon(newMailIcon);
						headPane.add(btnWriteEmail);
						ImageIcon returnIcon = new ImageIcon("src//main//java//views//homeIcon.png");
						Icon newReturnIcon = new ImageIcon(
								returnIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));

						contextMailPane = new JPanel();
						contentPane.add(contextMailPane, BorderLayout.SOUTH);

						mailListPane = new JPanel();

						/*
						 * Generate 2 JPanels: 1. JPanel = JPanel with JComboBox with all emails 2.
						 * JPanel = JPanel that show the context of message
						 */

						mailListPane = objectMail.generateJComboBoxWithEmails(contextMailPane, contentPane);

						/**
						 * Add listener to the Refresh button
						 */
						RefreshEmail.addRefreshButtonListener(btnRefresh, contentPane, contextMailPane);
						contentPane.add(mailListPane);

						OpenNewEmailListener.addNewMailOpenListener(btnWriteEmail);

						/**
						 * Create and start Thread that auto refresh the JComboBox with emails
						 */
						objectThreadAutoRefresh = new ThreadAutoRefresh(contentPane, contextMailPane);
						objectThreadAutoRefresh.start();
						setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Dirección de correo o contraseña incorrecta",
								"Login Fallido", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

	public static boolean isConnectMail() {
		return connectMail;
	}

	public static void setConnectMail(boolean connectMail) {
		InboxView.connectMail = connectMail;
	}

	public static JFrame getInstance() {
		return instance;
	}

	public static void setInstance(JFrame instance) {
		InboxView.instance = instance;
	}

}