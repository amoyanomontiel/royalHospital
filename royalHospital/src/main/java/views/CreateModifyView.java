package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.commons.net.ftp.FTPClient;

import listeners.ActionCreateDirectoryListener;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CreateModifyView extends JFrame{
	
	private JPanel contentPane;
	private JTextField textField;
	
	public CreateModifyView(String button, String label, String title, int type, FTPClient ftpClient) {
		setResizable(false);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 120);
		ImageIcon royal = new ImageIcon("src\\main\\java\\views\\ic_launcher.png");
		setIconImage(royal.getImage());
		setTitle("Royal Hospital - " + title);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(204, 204, 255));
		
		textField = new JTextField();
		textField.setBounds(22, 43, 228, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTitle = new JLabel(label);
		lblTitle.setBounds(12, 13, 370, 16);
		contentPane.add(lblTitle);
		
		JButton btnChange = new JButton(button);
		btnChange.setBounds(262, 40, 150, 30);
		contentPane.add(btnChange);
		if(type == 0) {
			//aqui listener del btnChange cuando se pulsa el botón de crear directorio
			//Hay que crear una carpeta en el servidor FTP y un nodo nuevo en el arbol o borrar el arbol y crearlo de nuevo
			btnChange.addActionListener(new ActionCreateDirectoryListener(ftpClient, textField.getText()));
			
		}else if(type == 1) {
			//aqui listener del btnChange cuando se pulsa el botón de crear fichero
			//Hay que crear una carpeta en el servidor FTP y un nodo nuevo en el arbol o borrar el arbol y crearlo de nuevo
		}else {
			//aqui listener del btnChange cuando se pulsa el botón de renombrar
			//Hay que cambiar el nombre de una carpeta en el servidor FTP y un nodo en el arbol o borrar el arbol y crearlo de nuevo
		}
		
	}
}
