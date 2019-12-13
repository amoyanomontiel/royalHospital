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

public class RoyalLoginView extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField textField_1;
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
		setBounds(100, 100, 380, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(172, 187, 116, 22);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(172, 233, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblUserName = new JLabel("Usuario");
		lblUserName.setBounds(89, 190, 56, 16);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setBounds(89, 236, 71, 16);
		contentPane.add(lblPassword);
		
		btnLogin = new JButton("Entrar");
		btnLogin.setBounds(133, 307, 97, 25);
		contentPane.add(btnLogin);	
		
		JLabel lblRoyalIcon = new JLabel();
		lblRoyalIcon.setBounds(70, 40, 237, 106);
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

	public JTextField getTextField_1() {
		return textField_1;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}
	
}
