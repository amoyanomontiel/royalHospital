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
import javax.swing.JComboBox;

public class InboxView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final int width = 30;
	private final int height = 30;
	private JPanel messagePanel;
	private JPanel headPane;
	private static MailMethods objectMail;

	
	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        objectMail = new MailMethods();
			        objectMail.setAllDataConnection("pop.gmail.com", "pop3", "jfernandezfernandez.sanjose@alumnado.fundacionloyola.net", "14674858");
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
//		contentPane.setLayout(new BorderLayout(0, 0));
		
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
		
		JPanel contextMailPane = new JPanel();
		contentPane.add(contextMailPane, BorderLayout.SOUTH);
		
		JPanel mailListPane = new JPanel();
		mailListPane = objectMail.generateJComboBoxWithEmails(contextMailPane, contentPane);

	}
	
}