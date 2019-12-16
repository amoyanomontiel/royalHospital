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
import javax.swing.JPasswordField;

public class RoyalLoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel loginPanel;
	private JTextField user_email_Text;
	private JButton login_Button;
	private JPasswordField password_Text;
	private JLabel icon;
	

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
		
		JLabel user_email_Label = new JLabel(user_email);
		user_email_Label.setFont(new Font("Tahoma", Font.BOLD, 15));
		user_email_Label.setBounds(35, 222, 150, 16);
		loginPanel.add(user_email_Label);
		
		user_email_Text = new JTextField();
		user_email_Text.setBounds(191, 220, 140, 22);
		loginPanel.add(user_email_Text);
		user_email_Text.setColumns(10);
		
		JLabel password_Label = new JLabel("Contraseña");
		password_Label.setFont(new Font("Tahoma", Font.BOLD, 15));
		password_Label.setBounds(35, 280, 91, 16);
		loginPanel.add(password_Label);
		
		ImageIcon img = new ImageIcon(RoyalLoginView.class.getResource(iconRoute));
		
		password_Text = new JPasswordField();
		password_Text.setBounds(191, 280, 140, 22);
		loginPanel.add(password_Text);
		
		login_Button = new JButton("Entrar");
		login_Button.setFont(new Font("Tahoma", Font.BOLD, 15));
		login_Button.setBounds(144, 367, 116, 32);
		loginPanel.add(login_Button);	
		
		icon = new JLabel();
		icon.setBounds(70, 40, 268, 117);
		Icon in = new ImageIcon(img.getImage().getScaledInstance(icon.getWidth(), icon.getHeight(), Image.SCALE_DEFAULT));
		icon.setIcon(in);
		loginPanel.add(icon);
	}

	public JPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(JPanel loginPanel) {
		this.loginPanel = loginPanel;
	}

	public JTextField getUser_email_Text() {
		return user_email_Text;
	}

	public void setUser_email_Text(JTextField user_email_Text) {
		this.user_email_Text = user_email_Text;
	}

	public JButton getLogin_Button() {
		return login_Button;
	}

	public void setLogin_Button(JButton login_Button) {
		this.login_Button = login_Button;
	}

	public JPasswordField getPassword_Text() {
		return password_Text;
	}

	public void setPassword_Text(JPasswordField password_Text) {
		this.password_Text = password_Text;
	}

	public JLabel getIcon() {
		return icon;
	}

	public void setIcon(JLabel icon) {
		this.icon = icon;
	}
}
