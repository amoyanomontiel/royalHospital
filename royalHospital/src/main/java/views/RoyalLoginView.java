package views;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RoyalLoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel loginPanel;
	private JTextField textFieldUser_email;
	private JButton btnLogin;
	private JPasswordField passwordField;
	private JLabel lblIcon;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoyalLoginView frame = new RoyalLoginView("Correo Electrónico", "/views/mailIcon.png", 100, 40, 183,
							117);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public void checkMail() {
		String userEmail = textFieldUser_email.getText();
		String password = passwordField.getText();

		if (StringUtils.isBlank(userEmail) || userEmail.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debes introducir una dirección de correo",
					"Introduzca dirección de correo", JOptionPane.WARNING_MESSAGE);
		}
		else if (!userEmail.contains("@gmail.com")) {
			JOptionPane.showMessageDialog(null, "Introduzca una cuenta de correo Gmail",
					"Introduzca dirección de correo correcta", JOptionPane.WARNING_MESSAGE);
		}
		else if (StringUtils.isBlank(password) || password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debes introducir una contraseña",
					"Introduzca contraseña", JOptionPane.WARNING_MESSAGE);
		}
		else {
			conections.EmailAuthenticator authentication = new conections.EmailAuthenticator(userEmail, password);
			int conectionFailed = authentication.AuthenticateEmail();
		}
	}

	/**
	 * Create the frame.
	 */
	public RoyalLoginView(String user_email, String iconRoute, int xPosition, int yPosition, int width, int height) {
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
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkMail();
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(144, 367, 116, 32);
		loginPanel.add(btnLogin);

		lblIcon = new JLabel();
		lblIcon.setBounds(xPosition, yPosition, width, height);
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

	public JTextField getUser_email_Text() {
		return textFieldUser_email;
	}

	public void setUser_email_Text(JTextField user_email_Text) {
		this.textFieldUser_email = user_email_Text;
	}

	public JButton getLogin_Button() {
		return btnLogin;
	}

	public void setLogin_Button(JButton login_Button) {
		this.btnLogin = login_Button;
	}

	public JPasswordField getPassword_Text() {
		return passwordField;
	}

	public void setPassword_Text(JPasswordField password_Text) {
		this.passwordField = password_Text;
	}

	public JLabel getIcon() {
		return lblIcon;
	}

	public void setIcon(JLabel icon) {
		this.lblIcon = icon;
	}
}
