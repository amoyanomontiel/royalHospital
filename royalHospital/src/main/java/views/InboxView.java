package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InboxView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final int width = 30;
	private final int height = 30;
	private JButton newMessageButton;
	private JButton closeInboxButton;
	private JButton returnInboxButton;
	private JPanel inboxMailsPanel;
	private JPanel messagePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InboxView frame = new InboxView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void showInbox(JButton returnInboxButton, JButton newMessageButton, JButton closeInboxButton, JPanel inboxMailsPanel, JPanel messagePanel) {
		returnInboxButton.setVisible(false);
		newMessageButton.setVisible(true);
		closeInboxButton.setVisible(true);
	}
	
	public void showMessage(JButton returnInboxButton, JButton newMessageButton, JButton closeInboxButton, JPanel inboxMailsPanel, JPanel messagePanel) {
		returnInboxButton.setVisible(true);
		newMessageButton.setVisible(false);
		closeInboxButton.setVisible(false);
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
		contentPane.setLayout(null);
		
		JPanel headPanel = new JPanel();
		headPanel.setBorder(null);
		headPanel.setBounds(0, 0, 836, 60);
		contentPane.add(headPanel);
		headPanel.setLayout(null);
		
		JLabel inboxLabel = new JLabel("Buzón De Entrada");
		inboxLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		inboxLabel.setBounds(45, 16, 142, 27);
		headPanel.add(inboxLabel);

		
		newMessageButton = new JButton("Redactar");
		newMessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMessage(returnInboxButton, newMessageButton, closeInboxButton, inboxMailsPanel, messagePanel);
			}
		});
		newMessageButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newMessageButton.setBounds(462, 10, 150, 40);
		ImageIcon newMailIcon = new ImageIcon("src/main/java/views/newGmail.png");
		Icon newIcon = new ImageIcon(newMailIcon.getImage().getScaledInstance((width+10), height, Image.SCALE_DEFAULT));
		newMessageButton.setIcon(newIcon);
		headPanel.add(newMessageButton);
		
		closeInboxButton = new JButton("Volver");
		closeInboxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		closeInboxButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		closeInboxButton.setBounds(660, 10, 150, 40);
		ImageIcon newHomeIcon = new ImageIcon("src/main/java/views/homeIcon.png");
		Icon newHome = new ImageIcon(newHomeIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		closeInboxButton.setIcon(newHome);
		headPanel.add(closeInboxButton);
		
		returnInboxButton = new JButton("Volver");
		returnInboxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showInbox(returnInboxButton, newMessageButton, closeInboxButton, inboxMailsPanel, messagePanel);
			}
		});
		returnInboxButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		returnInboxButton.setBounds(660, 10, 150, 40);
		ImageIcon newReturnIcon = new ImageIcon("src/main/java/views/return.png");
		Icon newReturn = new ImageIcon(newReturnIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		returnInboxButton.setIcon(newReturn);
		headPanel.add(returnInboxButton);
		
		JSeparator dividingLine = new JSeparator();
		dividingLine.setForeground(new Color(255, 0, 51));
		dividingLine.setBackground(new Color(255, 0, 51));
		dividingLine.setBounds(0, 60, 836, 2);
		contentPane.add(dividingLine);
		
		showInbox(returnInboxButton, newMessageButton, closeInboxButton, inboxMailsPanel, messagePanel);
		
		inboxMailsPanel = new JPanel();
		inboxMailsPanel.setBounds(0, 90, 836, 605);
		contentPane.add(inboxMailsPanel);
		inboxMailsPanel.setLayout(null);
		
		messagePanel = new JPanel();
		messagePanel.setBounds(0, 90, 836, 605);
		contentPane.add(messagePanel);
		messagePanel.setLayout(null);
	}
}
