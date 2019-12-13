package views;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class ErrorRoyalView extends JFrame{
	
	private JPanel contentPane;
	private JLabel lblMessage;
	
	public ErrorRoyalView(String error) {
		setResizable(false);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 220);
		setTitle("Error");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMessage = new JLabel("New label");
		lblMessage.setBounds(133, 60, 119, 22);
		contentPane.add(lblMessage);
		
		JLabel img_error = new JLabel("hola");
		img_error.setBounds(60, 48, 49, 46);
		
		ImageIcon img = new ImageIcon(ErrorRoyalView.class.getResource("/views/errorIcono.png"));
		Icon icon = new ImageIcon(img.getImage().getScaledInstance(img_error.getWidth(), img_error.getHeight(), Image.SCALE_DEFAULT));
		img_error.setIcon(icon);
		
		contentPane.add(img_error);
		
		JButton btnAccept = new JButton("New button");
		btnAccept.setBounds(133, 122, 97, 25);
		contentPane.add(btnAccept);
	}

	public JLabel getLblMessage() {
		return lblMessage;
	}

	public void setLblMessage(JLabel lblMessage) {
		this.lblMessage = lblMessage;
	}
	
	
}
