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
import java.awt.Font;

public class RoyalLoginView extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JButton btnLogin;
	/**
	 * Launch the application.
	 */
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
	public RoyalLoginView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 500);
		ImageIcon royal = new ImageIcon("src\\main\\java\\views\\ic_launcher.png");
		setIconImage(royal.getImage());
		setTitle("Login");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(191, 220, 116, 22);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(191, 278, 116, 22);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblUserName = new JLabel("Usuario");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUserName.setBounds(98, 222, 71, 16);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(98, 280, 91, 16);
		contentPane.add(lblPassword);
		
		btnLogin = new JButton("Entrar");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(144, 367, 116, 32);
		contentPane.add(btnLogin);	
		
		JLabel lblRoyalIcon = new JLabel();
		lblRoyalIcon.setBounds(70, 40, 268, 117);
		ImageIcon img = new ImageIcon(RoyalLoginView.class.getResource("/views/royalhospital.png"));
		Icon in = new ImageIcon(img.getImage().getScaledInstance(lblRoyalIcon.getWidth(), lblRoyalIcon.getHeight(), Image.SCALE_DEFAULT));
		lblRoyalIcon.setIcon(in);
		contentPane.add(lblRoyalIcon);
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

	public void setTxtPassword(JTextField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}
}
