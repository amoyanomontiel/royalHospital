package views;

import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.commons.net.ftp.FTPClient;

import com.royalhospital.royalHospital.DataModel;

import listeners.ActionCreateDirectoryListener;
import listeners.ActionCreateFileListener;
import listeners.ActionRenameFileListener;
import listeners.ConfirmDeleteListener;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dialog;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
/**
 * 
 * @author Daniel Cuenca / Cristina Montilla
 *
 */
public class CreateModifyView extends JDialog{
	
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnOk;
	JButton btnCancel;
	DataModel data = new DataModel();
	
	public CreateModifyView(String button, String label, String title, int type, FTPClient ftpClient, MainRoyalView royalView) {
		setResizable(false);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 430, 120);
		setLocationRelativeTo(null);
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
			btnChange.addActionListener(new ActionCreateDirectoryListener(ftpClient, textField, royalView, this));		
		}else if(type == 1) {
			btnChange.addActionListener(new ActionCreateFileListener(ftpClient, textField, royalView, this));
		}else {
			textField.setText(DataModel.selectedFile);
			btnChange.addActionListener(new ActionRenameFileListener(ftpClient, textField, royalView, this));
		}
		
	}
	
	public CreateModifyView (FTPClient ftpClient, MainRoyalView principalFrame, String fileType) {
		setResizable(false);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 430, 120);
		setLocationRelativeTo(null);
		ImageIcon royal = new ImageIcon("src\\main\\java\\views\\ic_launcher.png");
		setIconImage(royal.getImage());
		setTitle("Royal Hospital - " + data.getDeleteTag());
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(204, 204, 255));
		
		JLabel lblTitle = new JLabel(data.getDeleteQuestion());
		lblTitle.setBounds(110, 13, 370, 16);
		contentPane.add(lblTitle);
		
		btnOk = new JButton("Aceptar");
		btnOk.setBounds(40, 40, 150, 30);
		contentPane.add(btnOk);
		btnOk.addActionListener(new ConfirmDeleteListener(btnOk, this, ftpClient, principalFrame, fileType));
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(230, 40, 150, 30);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ConfirmDeleteListener(btnCancel, this, ftpClient, principalFrame, fileType));
	}	
}
