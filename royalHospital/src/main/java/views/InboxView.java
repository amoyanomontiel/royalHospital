package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.royalhospital.royalHospital.MailMethods;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class InboxView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final int width = 30;
	private final int height = 30;
	private JPanel inboxMailsPanel;
	private JPanel messagePanel;
	private JPanel headPanel;

	
	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        MailMethods objetoMail = new MailMethods();
			        objetoMail.setAllDataConnection("pop.gmail.com", "pop3", "jfernandezfernandez.sanjose@alumnado.fundacionloyola.net", "14674858");
			        objetoMail.setProperties();
			        objetoMail.connectMailServer();
			        objetoMail.setFolderEmails();
			        objetoMail.receiveAndSaveAllEmails();
					InboxView frame = new InboxView(objetoMail);
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
	public InboxView(MailMethods objectMail) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 748);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon newMailIcon = new ImageIcon("src/main/java/views/newGmail.png");
		Icon newIcon = new ImageIcon(newMailIcon.getImage().getScaledInstance((width+10), height, Image.SCALE_DEFAULT));
		ImageIcon newHomeIcon = new ImageIcon("src/main/java/views/homeIcon.png");
		Icon newHome = new ImageIcon(newHomeIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		ImageIcon newReturnIcon = new ImageIcon("src/main/java/views/return.png");
		Icon newReturn = new ImageIcon(newReturnIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		contentPane.setLayout(new BorderLayout(0, 0));
		inboxMailsPanel.setBounds(0, 90, 836, 605);
		contentPane.add(inboxMailsPanel);
		
		headPanel = new JPanel();
		contentPane.add(headPanel, BorderLayout.NORTH);
		
		JLabel lblInbox = new JLabel("Buzón de entrada");
		lblInbox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		headPanel.add(lblInbox);
		
		JButton btnRefresh = new JButton("Refrescar");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		headPanel.add(btnRefresh);
		
		JButton btnWriteEmail = new JButton("Redactar");
		btnWriteEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		headPanel.add(btnWriteEmail);
		
		JButton btnCloseInbox = new JButton("Volver");
		btnCloseInbox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		headPanel.add(btnCloseInbox);
		
		JPanel contextMail = new JPanel();
		contentPane.add(contextMail, BorderLayout.CENTER);


	}
	
}