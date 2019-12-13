package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class RoyalLoginView extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoyalLoginView frame = new RoyalLoginView();
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
	public RoyalLoginView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(172, 158, 116, 22);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(172, 204, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblUserName = new JLabel("Usuario");
		lblUserName.setBounds(89, 161, 56, 16);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setBounds(89, 207, 71, 16);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setBounds(137, 283, 97, 25);
		contentPane.add(btnLogin);
	}
}
