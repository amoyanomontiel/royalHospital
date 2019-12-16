package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import Listeners.DocumentsListener;
import Listeners.DownloadListener;
import Listeners.UploadListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

public class MainRoyalView extends JFrame {

	private static final int BTN_HEIGHT = 40;
	private static final int IMG_HEIGHT = 30;
	private static final int BTN_WIDTH = 150;
	private static final int IMG_WIDTH = 30;
	private JPanel contentPane;
	private static DefaultMutableTreeNode raiz;
	
	/**
	 * Create the frame.
	 * @param ftpClient 
	 */
	public MainRoyalView() {
		
	}
	
	public MainRoyalView(FTPClient ftpClient, String user) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 748);
		setTitle("Royal Hospital - Main");
		ImageIcon royal = new ImageIcon("src\\main\\java\\views\\ic_launcher.png");
		setIconImage(royal.getImage());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 86, 691, 259);
		contentPane.add(scrollPane);
		
		raiz = new DefaultMutableTreeNode("Royal Hospital");
		if(ftpClient.isConnected()) {
			try {			
				seekFile(raiz, ftpClient.listFiles(), ftpClient);
			} catch (IOException e) {
				ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar con el servidor FTP", 0);
				error.setVisible(true);
				error.setLocationRelativeTo(null);
			}
			JTree tree = new JTree(raiz);
			scrollPane.setViewportView(tree);
		}		

		try {			
			seekFile(raiz, ftpClient.listFiles(), ftpClient);
		} catch (IOException e) {
			ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar con el servidor FTP", 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
		JTree tree = new JTree(raiz);
		scrollPane.setViewportView(tree);		
		JTextArea txtaHistorial = new JTextArea();
		txtaHistorial.setEditable(false);
		txtaHistorial.setBounds(64, 363, 691, 134);
		contentPane.add(txtaHistorial);
		
		JButton btnUpload = new JButton("Cargar");
		contentPane.add(btnUpload);
		btnUpload.setBackground(Color.WHITE);
		btnUpload.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpload.addActionListener(new UploadListener());
		
		JButton btnDownload = new JButton("Descargar");
		contentPane.add(btnDownload);
		btnDownload.setBackground(Color.WHITE);
		btnDownload.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDownload.addActionListener(new DownloadListener());

		
		JButton btnRemove = new JButton("Borrar");
		contentPane.add(btnRemove);
		btnRemove.setBackground(Color.WHITE);
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnCreateDir = new JButton("Crear Directorio");
		contentPane.add(btnCreateDir);
		btnCreateDir.setBackground(Color.WHITE);
		btnCreateDir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnCreateFile = new JButton("Crear Fichero");
		contentPane.add(btnCreateFile);
		btnCreateFile.setBackground(Color.WHITE);
		btnCreateFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnRename = new JButton("Renombrar");
		contentPane.add(btnRename);
		btnRename.setBackground(Color.WHITE);
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnDocuments = new JButton("Documentos");
		contentPane.add(btnDocuments);
		btnDocuments.setBackground(Color.WHITE);
		btnDocuments.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDocuments.addActionListener(new DocumentsListener(raiz, user));
		
		JButton btnPatient = new JButton("Pacientes");
		contentPane.add(btnPatient);
		btnPatient.setBackground(Color.WHITE);
		btnPatient.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnMail = new JButton("Correo");
		contentPane.add(btnMail);
		btnMail.setBackground(Color.WHITE);
		btnMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnDocuments.setBounds(64,13, BTN_WIDTH, BTN_HEIGHT);
		btnPatient.setBounds(240,13, BTN_WIDTH, BTN_HEIGHT);
		btnMail.setBounds(604,13, BTN_WIDTH, BTN_HEIGHT);
		btnUpload.setBounds(64,585, BTN_WIDTH, BTN_HEIGHT);
		btnDownload.setBounds(240,585, BTN_WIDTH, BTN_HEIGHT);
		btnRemove.setBounds(425,585, BTN_WIDTH, BTN_HEIGHT);
		btnRename.setBounds(604,585, BTN_WIDTH, BTN_HEIGHT);
		btnCreateDir.setBounds(425, 524, BTN_WIDTH, BTN_HEIGHT);
		btnCreateFile.setBounds(604, 524, BTN_WIDTH, BTN_HEIGHT);
		
		ImageIcon mydoc = new ImageIcon("src\\main\\java\\views\\doc4.png");
		Icon mydocIcon = new ImageIcon(mydoc.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		btnDocuments.setIcon(mydocIcon);
		
		ImageIcon mydoc2 = new ImageIcon("src\\main\\java\\views\\doc_patient.png");
		Icon mydocIcon2 = new ImageIcon(mydoc2.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		btnPatient.setIcon(mydocIcon2);
		
		ImageIcon mydoc3 = new ImageIcon("src\\main\\java\\views\\gmail.png");
		Icon mydocIcon3 = new ImageIcon(mydoc3.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		btnMail.setIcon(mydocIcon3);
	
		ImageIcon mydoc4 = new ImageIcon("src\\main\\java\\views\\upload3.png");
		Icon mydocIcon4 = new ImageIcon(mydoc4.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		btnUpload.setIcon(mydocIcon4);
		
		ImageIcon mydoc5 = new ImageIcon("src\\main\\java\\views\\download2.png");
		Icon mydocIcon5 = new ImageIcon(mydoc5.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		btnDownload.setIcon(mydocIcon5);
	}
	/**
	 * Metodo que rellena el JTree con los nodos correspondientes
	 * 
	 * @param raiz2
	 * @param files
	 * @param ftpClient 
	 */
	private void seekFile(DefaultMutableTreeNode raiz2,FTPFile[] files, FTPClient ftpClient){
		FTPFile[] list = files;
		if(list!=null)
			for (FTPFile fil : list){
				if (fil.isDirectory()){
					DefaultMutableTreeNode carpeta = new DefaultMutableTreeNode(fil.getName());
					raiz2.add(carpeta);
					FTPFile[] list2 = null;
					try {
						ftpClient.changeWorkingDirectory(fil.getName());
						list2 = ftpClient.listFiles();
					} catch (IOException e) {
						ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar con el servidor FTP", 0);
						error.setVisible(true);
						error.setLocationRelativeTo(null);
					}
					seekFile(carpeta,list2, ftpClient);
					}
				else {
					DefaultMutableTreeNode archivo = new DefaultMutableTreeNode(fil.getName());
					raiz2.add(archivo);
	            }
	        }
	}

	public DefaultMutableTreeNode getRaiz() {
		return raiz;
	}
	public void setRaiz(DefaultMutableTreeNode raiz) {
		this.raiz = raiz;
	}
}
