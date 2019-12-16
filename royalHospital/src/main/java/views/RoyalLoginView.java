package views;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Listeners.LoginListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Font;
import javax.swing.JPasswordField;

public class RoyalLoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel loginPanel;
	private JTextField textFieldUser_email;
	private JButton btnLogin;
	private JPasswordField passwordField;
	private JLabel lblIcon;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RoyalLoginView frame = new RoyalLoginView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public RoyalLoginView(String user_email, String iconRoute) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 500);
		ImageIcon royal = new ImageIcon("src\\main\\java\\views\\ic_launcher.png");
		setIconImage(royal.getImage());
		setTitle("Login");
		loginPanel = new JPanel();
		loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(loginPanel);
		loginPanel.setLayout(null);

		JLabel lblUser_email = new JLabel(user_email);
		lblUser_email.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUser_email.setBounds(35, 222, 150, 16);
		loginPanel.add(lblUser_email);

		textFieldUser_email = new JTextField();
		textFieldUser_email.setBounds(191, 220, 140, 22);
		loginPanel.add(textFieldUser_email);
		textFieldUser_email.setColumns(10);

		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(35, 280, 91, 16);
		loginPanel.add(lblPassword);

		ImageIcon img = new ImageIcon(RoyalLoginView.class.getResource(iconRoute));

		passwordField = new JPasswordField();
		passwordField.setBounds(191, 280, 140, 22);
		loginPanel.add(passwordField);

		btnLogin = new JButton("Entrar");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(144, 367, 116, 32);
		btnLogin.addActionListener(new LoginListener(this));
		loginPanel.add(btnLogin);

		lblIcon = new JLabel();
		lblIcon.setBounds(70, 40, 268, 117);
		Icon in = new ImageIcon(
				img.getImage().getScaledInstance(lblIcon.getWidth(), lblIcon.getHeight(), Image.SCALE_DEFAULT));
		lblIcon.setIcon(in);
		loginPanel.add(lblIcon);
	}

	public JPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(JPanel loginPanel) {
		this.loginPanel = loginPanel;
	}

	public JTextField getTextFieldUser_email() {
		return textFieldUser_email;
	}

	public void setTextFieldUser_email(JTextField textFieldUser_email) {
		this.textFieldUser_email = textFieldUser_email;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JLabel getLblIcon() {
		return lblIcon;
	}

	public void setLblIcon(JLabel lblIcon) {
		this.lblIcon = lblIcon;
	}
	
}
