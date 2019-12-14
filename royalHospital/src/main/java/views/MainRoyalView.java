package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

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
	private ArrayList<JButton> buttons = new ArrayList<>();
	private static DefaultMutableTreeNode raiz;
	
	/**
	 * Create the frame.
	 * @param ftpClient 
	 */
	public MainRoyalView(FTPClient ftpClient) {//Hay que pasarle por parametro la direccion(path) de la carpeta raiz que le pertenece 
		//segun los permisos que tenga
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 748);
		setTitle("Royal Hospital");
		ImageIcon royal = new ImageIcon("src\\main\\java\\views\\ic_launcher.png");
		setIconImage(royal.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 86, 691, 259);
		contentPane.add(scrollPane);
		
		raiz = new DefaultMutableTreeNode("Royal Hospital");
		try {			
			seekFile(raiz, ftpClient.listFiles(), ftpClient);
		} catch (IOException e) {
			ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar con el servidor FTP");
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
		JTree tree = new JTree(raiz);
		scrollPane.setViewportView(tree);
		
		JTextArea txtaHistorial = new JTextArea();
		txtaHistorial.setEditable(false);
		txtaHistorial.setBounds(64, 363, 691, 134);
		contentPane.add(txtaHistorial);
		
		buttons.add(new JButton("Documentos"));
		buttons.add(new JButton("Pacientes"));
		buttons.add(new JButton("Correo"));
		buttons.add(new JButton("Cargar"));
		buttons.add(new JButton("Descargar"));
		buttons.add(new JButton("Borrar"));
		buttons.add(new JButton("Renombrar"));
		buttons.add(new JButton("Crear Directorio"));
		buttons.add(new JButton("Crear Fichero"));
		
		buttons.get(0).setBounds(64,13, BTN_WIDTH, BTN_HEIGHT);
		buttons.get(1).setBounds(240,13, BTN_WIDTH, BTN_HEIGHT);
		buttons.get(2).setBounds(604,13, BTN_WIDTH, BTN_HEIGHT);
		buttons.get(3).setBounds(64,585, BTN_WIDTH, BTN_HEIGHT);
		buttons.get(4).setBounds(240,585, BTN_WIDTH, BTN_HEIGHT);
		buttons.get(5).setBounds(425,585, BTN_WIDTH, BTN_HEIGHT);
		buttons.get(6).setBounds(604,585, BTN_WIDTH, BTN_HEIGHT);
		buttons.get(7).setBounds(425, 524, BTN_WIDTH, BTN_HEIGHT);
		buttons.get(8).setBounds(604, 524, BTN_WIDTH, BTN_HEIGHT);
		
		ImageIcon mydoc = new ImageIcon("src\\main\\java\\views\\doc4.png");
		Icon mydocIcon = new ImageIcon(mydoc.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		buttons.get(0).setIcon(mydocIcon);
		
		ImageIcon mydoc2 = new ImageIcon("src\\main\\java\\views\\doc_patient.png");
		Icon mydocIcon2 = new ImageIcon(mydoc2.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		buttons.get(1).setIcon(mydocIcon2);
		
		ImageIcon mydoc3 = new ImageIcon("src\\main\\java\\views\\gmail.png");
		Icon mydocIcon3 = new ImageIcon(mydoc3.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		buttons.get(2).setIcon(mydocIcon3);
		
		ImageIcon mydoc4 = new ImageIcon("src\\main\\java\\views\\upload3.png");
		Icon mydocIcon4 = new ImageIcon(mydoc4.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		buttons.get(3).setIcon(mydocIcon4);
		
		ImageIcon mydoc5 = new ImageIcon("src\\main\\java\\views\\download2.png");
		Icon mydocIcon5 = new ImageIcon(mydoc5.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		buttons.get(4).setIcon(mydocIcon5);
		
		for(JButton a: buttons) {
			a.setBackground(Color.WHITE);
			a.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPane.add(a);
		}
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
					seekFTPFile(carpeta,fil, ftpClient);
					}
				else {
					DefaultMutableTreeNode archivo = new DefaultMutableTreeNode(fil.getName());
					raiz2.add(archivo);
	            }
	        }
	}
	
	private void seekFTPFile(DefaultMutableTreeNode carpeta, FTPFile fil, FTPClient ftpClient) {
		try {
			ftpClient.changeWorkingDirectory(fil.getName());
			FTPFile[] list = ftpClient.listFiles();
			if(list!=null)
				for (FTPFile file : list){
					if (file.isDirectory()){
						DefaultMutableTreeNode carpeta2 = new DefaultMutableTreeNode(file.getName());
						carpeta.add(carpeta2);
						seekFTPFile(carpeta2,file, ftpClient);
						}
					else {
						DefaultMutableTreeNode archivo = new DefaultMutableTreeNode(file.getName());
						carpeta.add(archivo);
		            }
		        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}
	public DefaultMutableTreeNode getRaiz() {
		return raiz;
	}
	public void setRaiz(DefaultMutableTreeNode raiz) {
		this.raiz = raiz;
	}
}
