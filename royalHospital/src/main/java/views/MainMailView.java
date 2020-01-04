package views;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JPasswordField;

/**
 * Login view class which define all frame properties
 * 
 * @author Cristina Montilla
 *
 */
public class MainMailView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JButton btnLogin;
	private JPasswordField txtPassword;

	/**
	 * Creates the login frame
	 * @param String user_email 
	 * @param String iconRoute Route of the icon
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMailView frame = new MainMailView("Correo Electrónico", "/views/mailIcon.png");
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}
	
	public MainMailView(String user_email, String iconRoute) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 500);
		ImageIcon royal = new ImageIcon("src\\main\\java\\views\\ic_launcher.png");
		setIconImage(royal.getImage());
		setTitle("Royal Hospital - Gmail Login");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUserName = new JTextField();
		txtUserName.setBounds(191, 220, 147, 22);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);

		JLabel lblUserName = new JLabel(user_email);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUserName.setBounds(32, 220, 149, 16);
		contentPane.add(lblUserName);

		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(32, 278, 91, 16);
		contentPane.add(lblPassword);

		btnLogin = new JButton("Entrar");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(144, 367, 116, 32);
		contentPane.add(btnLogin);
//		btnLogin.addActionListener(new LoginListener(this));

		JLabel lblRoyalIcon = new JLabel();
		lblRoyalIcon.setBounds(90, 35, 268, 137);
		ImageIcon img = new ImageIcon(MainMailView.class.getResource(iconRoute));
		Icon in = new ImageIcon(img.getImage().getScaledInstance(lblRoyalIcon.getWidth()-50, lblRoyalIcon.getHeight()+40,
				Image.SCALE_DEFAULT));
		lblRoyalIcon.setIcon(in);
		contentPane.add(lblRoyalIcon);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(191, 278, 147, 22);
		contentPane.add(txtPassword);
	}

	public JTextField getTxtUserName() {
		return txtUserName;
	}

	public void setTxtUserName(JTextField txtUserName) {
		this.txtUserName = txtUserName;
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(JPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}
}
