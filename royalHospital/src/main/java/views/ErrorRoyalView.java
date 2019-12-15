package views;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;

public class ErrorRoyalView extends JFrame {
	
	private JPanel contentPane;
	
	public ErrorRoyalView(String error) {
		setResizable(false);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 220);
		ImageIcon royal = new ImageIcon("src\\main\\java\\views\\ic_launcher.png");
		setIconImage(royal.getImage());
		setTitle("Error");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMessage = new JLabel(error);
		lblMessage.setBounds(95, 48, 350, 46);
		contentPane.add(lblMessage);
		
		JLabel img_error = new JLabel();
		img_error.setBounds(34, 48, 49, 46);
		
		ImageIcon img = new ImageIcon(ErrorRoyalView.class.getResource("/views/errorIcono.png"));
		Icon icon = new ImageIcon(img.getImage().getScaledInstance(img_error.getWidth(), img_error.getHeight(), Image.SCALE_DEFAULT));
		img_error.setIcon(icon);
		
		contentPane.add(img_error);
		
		JButton btnAccept = new JButton("Aceptar");
		btnAccept.setBounds(190, 123, 100, 30);
		contentPane.add(btnAccept);
		
		btnAccept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
}
