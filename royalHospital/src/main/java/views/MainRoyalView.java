package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import com.royalhospital.royalHospital.DataModel;

import conections.FTPConection;
import listeners.CreateDirectoryListener;
import listeners.CreateFileListener;
import listeners.DocumentsListener;
import listeners.DownloadListener;
import listeners.PatientsListener;
import listeners.RemoveListener;
import listeners.RenameListener;
import listeners.UploadListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

/**
 * Principal view class which define all frame properties
 * 
 * @author Cristina Montilla / Daniel Cuenca
 *
 */
public class MainRoyalView extends JFrame implements TreeSelectionListener {
	/**
	 * Principal class variables
	 */
	private static final int BTN_HEIGHT = 40;
	private static final int IMG_HEIGHT = 30;
	private static final int BTN_WIDTH = 150;
	private static final int IMG_WIDTH = 30;
	private static final int IMG_WIDTH_EXIT = 86;
	private static final int IMG_HEIGHT_EXIT = 86;
	private static final int BTN_WIDTH_EXIT = 54;
	private static final int BTN_HEIGHT_EXIT = 54;
	private JPanel contentPane;
	private static DefaultMutableTreeNode root;
	private static JTextArea txtaHistorial;
	private static JTree tree;
	private static JScrollPane scrollPane, scrollHistory;
	DataModel data = new DataModel();

	public MainRoyalView() {

	}

	/**
	 * Creates the principal frame and the tree of server files
	 * 
	 * @param FTPClient ftpClient Client ftp for transfer operations
	 * @param String    user Username
	 * @param String    roll User roll
	 */
	public MainRoyalView(FTPClient ftpClient, String user, String roll) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 748);
		setTitle(data.getMainTitle());
		ImageIcon royal = new ImageIcon(data.getIconRoyalLogo());
		setIconImage(royal.getImage());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 86, 691, 259);
		contentPane.add(scrollPane);

		txtaHistorial = new JTextArea();
		txtaHistorial.setEditable(false);

		scrollHistory = new JScrollPane();
		scrollHistory.setBounds(64, 363, 691, 134);
		scrollHistory.setViewportView(txtaHistorial);
		contentPane.add(scrollHistory);

		JButton btnUpload = new JButton(data.getUploadTag());
		contentPane.add(btnUpload);
		btnUpload.setBackground(Color.WHITE);
		btnUpload.setFont(new Font(data.getFontType(), Font.PLAIN, 15));
		btnUpload.addActionListener(new UploadListener(this, ftpClient));

		JButton btnDownload = new JButton(data.getDownloadTag());
		contentPane.add(btnDownload);
		btnDownload.setBackground(Color.WHITE);
		btnDownload.setFont(new Font(data.getFontType(), Font.PLAIN, 15));
		btnDownload.addActionListener(new DownloadListener(this, ftpClient));

		JButton btnRemove = new JButton(data.getDeleteTag());
		contentPane.add(btnRemove);
		btnRemove.setBackground(Color.WHITE);
		btnRemove.setFont(new Font(data.getFontType(), Font.PLAIN, 15));
		btnRemove.addActionListener(new RemoveListener(this, ftpClient));

		JButton btnCreateDir = new JButton(data.getCreateFolderTag());
		contentPane.add(btnCreateDir);
		btnCreateDir.setBackground(Color.WHITE);
		btnCreateDir.setFont(new Font(data.getFontType(), Font.PLAIN, 15));
		btnCreateDir.addActionListener(new CreateDirectoryListener(this, ftpClient));

		JButton btnCreateFile = new JButton(data.getCreateFileTag());
		contentPane.add(btnCreateFile);
		btnCreateFile.setBackground(Color.WHITE);
		btnCreateFile.setFont(new Font(data.getFontType(), Font.PLAIN, 15));
		btnCreateFile.addActionListener(new CreateFileListener(this, ftpClient));

		JButton btnRename = new JButton(data.getRenameTag());
		contentPane.add(btnRename);
		btnRename.setBackground(Color.WHITE);
		btnRename.setFont(new Font(data.getFontType(), Font.PLAIN, 15));
		btnRename.addActionListener(new RenameListener(this, ftpClient));

		JButton btnDocuments = new JButton(data.getDocumentsTag());
		contentPane.add(btnDocuments);
		btnDocuments.setBackground(Color.WHITE);
		btnDocuments.setFont(new Font(data.getFontType(), Font.PLAIN, 15));
		btnDocuments.addActionListener(new DocumentsListener(user, ftpClient, this));

		JButton btnPatient = new JButton(data.getPatientsTag());
		contentPane.add(btnPatient);
		btnPatient.setBackground(Color.WHITE);
		btnPatient.setFont(new Font(data.getFontType(), Font.PLAIN, 15));
		btnPatient.addActionListener(new PatientsListener(ftpClient, this));

		JButton btnMail = new JButton(data.getMailTag());
		contentPane.add(btnMail);
		btnMail.setBackground(Color.WHITE);
		btnMail.setFont(new Font(data.getFontType(), Font.PLAIN, 15));
		btnMail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Añadir Correo
			}
		});
		
		JButton btnLogout = new JButton();
		contentPane.add(btnLogout);
		btnLogout.setBackground(Color.WHITE);
		btnLogout.setFont(new Font(data.getFontType(), Font.PLAIN, 15));
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new FTPConection().disconnectFTP();
				RoyalLoginView login = new RoyalLoginView(data.getUserTag(), data.getBigRoyalLogoRoute());
				login.setLocationRelativeTo(null);
				login.setVisible(true);	
			}
		});
		btnLogout.setToolTipText(data.getLogoutTag());
		

		btnDocuments.setBounds(64, 13, BTN_WIDTH, BTN_HEIGHT);
		btnPatient.setBounds(240, 13, BTN_WIDTH, BTN_HEIGHT);
		btnMail.setBounds(604, 13, BTN_WIDTH, BTN_HEIGHT);
		btnUpload.setBounds(64, 585, BTN_WIDTH, BTN_HEIGHT);
		btnDownload.setBounds(240, 585, BTN_WIDTH, BTN_HEIGHT);
		btnRemove.setBounds(425, 585, BTN_WIDTH, BTN_HEIGHT);
		btnRename.setBounds(604, 585, BTN_WIDTH, BTN_HEIGHT);
		btnCreateDir.setBounds(425, 524, BTN_WIDTH, BTN_HEIGHT);
		btnCreateFile.setBounds(604, 524, BTN_WIDTH, BTN_HEIGHT);
		btnLogout.setBounds(699, 645, BTN_WIDTH_EXIT, BTN_HEIGHT_EXIT);

		ImageIcon mydoc = new ImageIcon("src\\main\\java\\views\\doc4.png");
		Icon mydocIcon = new ImageIcon(mydoc.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		btnDocuments.setIcon(mydocIcon);

		ImageIcon mydoc2 = new ImageIcon("src\\main\\java\\views\\doc_patient.png");
		Icon mydocIcon2 = new ImageIcon(
				mydoc2.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		btnPatient.setIcon(mydocIcon2);

		ImageIcon mydoc3 = new ImageIcon("src\\main\\java\\views\\gmail.png");
		Icon mydocIcon3 = new ImageIcon(
				mydoc3.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		btnMail.setIcon(mydocIcon3);

		ImageIcon mydoc4 = new ImageIcon("src\\main\\java\\views\\upload3.png");
		Icon mydocIcon4 = new ImageIcon(
				mydoc4.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		btnUpload.setIcon(mydocIcon4);

		ImageIcon mydoc5 = new ImageIcon("src\\main\\java\\views\\download2.png");
		Icon mydocIcon5 = new ImageIcon(
				mydoc5.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_DEFAULT));
		btnDownload.setIcon(mydocIcon5);

		ImageIcon mydoc6 = new ImageIcon("src\\main\\java\\views\\IconoExit.png");
		Icon mydocIcon6 = new ImageIcon(
				mydoc6.getImage().getScaledInstance(IMG_WIDTH_EXIT, IMG_HEIGHT_EXIT, Image.SCALE_DEFAULT));
		btnLogout.setIcon(mydocIcon6);

		if (roll.equalsIgnoreCase(data.getDoctorTag())) {
			root = new DefaultMutableTreeNode("/Medicos/" + user);
		} else {
			root = new DefaultMutableTreeNode("/Pacientes/" + user);
		}

		if (ftpClient.isConnected()) {
			try {
				if (roll.equalsIgnoreCase(data.getDoctorTag())) {
					ftpClient.changeWorkingDirectory("/Medicos/" + user);
					DataModel.directionPath = ftpClient.printWorkingDirectory();
					createFilesTree(root, ftpClient.listFiles(), ftpClient);
					ftpClient.changeWorkingDirectory("/Medicos/" + user);
				} else if (roll.equalsIgnoreCase(data.getPatientTag())) {
					ftpClient.changeWorkingDirectory("/Pacientes/" + user);
					DataModel.directionPath = ftpClient.printWorkingDirectory();
					createFilesTree(root, ftpClient.listFiles(), ftpClient);
					ftpClient.changeWorkingDirectory("/Pacientes/" + user);

					btnCreateDir.setEnabled(false);
					btnCreateFile.setEnabled(false);
					btnDocuments.setEnabled(false);
					btnPatient.setEnabled(false);
					btnRemove.setEnabled(false);
					btnRename.setEnabled(false);
				}
			} catch (IOException | NullPointerException e) {
				ErrorRoyalView error = new ErrorRoyalView(data.getFtpConectionError(), 0);
				error.setVisible(true);
				error.setLocationRelativeTo(null);
			}
			tree = new JTree(root);
			changeStyleJtree(tree);
			scrollPane.setViewportView(tree);
			tree.addTreeSelectionListener(this);
		}
	}

	/**
	 * Function which search and creates all nodes of the server files tree
	 * 
	 * @param DefaultMutableTreeNode root2 Root node
	 * @param FTPFile[]              files List of server files
	 * @param FTPClient              ftpClient Client ftp object
	 */
	public void createFilesTree(DefaultMutableTreeNode root2, FTPFile[] files, FTPClient ftpClient) {
		FTPFile[] list = files;
		if (list != null)
			for (FTPFile fil : list) {
				if (fil.isDirectory()) {
					DefaultMutableTreeNode directory = new DefaultMutableTreeNode(fil.getName());
					root2.add(directory);
					try {
						ftpClient.changeWorkingDirectory(fil.getName());
						FTPFile[] list2 = ftpClient.listFiles();
						createFilesTree(directory, list2, ftpClient);
					} catch (IOException e) {
						ErrorRoyalView error = new ErrorRoyalView(data.getFtpConectionError(), 0);
						error.setVisible(true);
						error.setLocationRelativeTo(null);
					}
				} else {
					DefaultMutableTreeNode file = new DefaultMutableTreeNode(fil.getName());
					root2.add(file);
				}
			}
		try {
			ftpClient.changeToParentDirectory();
		} catch (IOException e) {
			ErrorRoyalView error = new ErrorRoyalView(data.getFtpConectionError(), 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
	}
	
	private void changeStyleJtree(JTree tree) {
		DefaultTreeCellRenderer cell = (DefaultTreeCellRenderer)tree.getCellRenderer();
		ImageIcon directoryClose = new ImageIcon("src\\main\\java\\views\\IconoCarpetaCerrada.png");
		ImageIcon directoryOpen = new ImageIcon("src\\main\\java\\views\\IconoCarpetaAbierta.png");
		ImageIcon file = new ImageIcon("src\\main\\java\\views\\IconoArchivo.png");
		cell.setClosedIcon(directoryClose);
		cell.setOpenIcon(directoryOpen);
		cell.setLeafIcon(file);
		final Font currentFont = tree.getFont();
		final Font bigFont = new Font(currentFont.getName(), currentFont.getStyle(), currentFont.getSize() + 1);
		tree.setFont(bigFont);
		tree.setCellRenderer(cell);
	}
	

	/**
	 * Creates new JTree
	 * 
	 * @param DefaultMutableTreeNode completeTree The whole tree
	 */
	public void changedJTree(DefaultMutableTreeNode wholeTree) {
		tree = new JTree(wholeTree);
		changeStyleJtree(tree);
		scrollPane.setViewportView(tree);
		tree.addTreeSelectionListener(this);
	}

	/**
	 * Refresh JTree for updates, create directory or create file
	 * 
	 * @param String nameNode New node's name
	 */
	public void refreshJTree(String nameNode) {
		DefaultTreeModel model = (DefaultTreeModel) MainRoyalView.tree.getModel();
		DefaultMutableTreeNode newNode = (DefaultMutableTreeNode) MainRoyalView.tree.getLastSelectedPathComponent();
		model.insertNodeInto(new DefaultMutableTreeNode(nameNode), newNode, newNode.getChildCount());
	}

	/**
	 * Click event function for Jtree which save diferents roots and name of clicked
	 * file
	 */
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		String path = "";
		JTree atree = (JTree) e.getSource();
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) atree.getLastSelectedPathComponent();
		if (selectedNode != null) {
			DataModel.selectedFile = selectedNode.toString();
			TreeNode[] route = selectedNode.getPath();
			for (int i = 0; i < route.length; i++) {
				System.out.println(path);
				if (i == route.length - 1) {
					DataModel.directionPath = path;
				}
				path += "/" + route[i];
			}
			DataModel.actualUserPath = path;
			txtaHistorial.append(data.getSelectedFileMsg() + DataModel.selectedFile + "\n");
		}
	}

	/**
	 * Function which changes to blank or empty the values of DataModel roots
	 * variables
	 */
	public void rootsToBlank() {
		DataModel.directionPath = "";
		DataModel.actualUserPath = "";
		DataModel.selectedFile = "";
		tree.clearSelection();
	}

	public DefaultMutableTreeNode getRaiz() {
		return root;
	}

	public void setRaiz(DefaultMutableTreeNode raiz) {
		this.root = raiz;
	}

	public JTextArea getTxtaHistorial() {
		return txtaHistorial;
	}

	public static void setTxtaHistorial(JTextArea txtaHistorial) {
		MainRoyalView.txtaHistorial = txtaHistorial;
	}

	public static JTree getTree() {
		return tree;
	}

	public static void setTree(JTree tree) {
		MainRoyalView.tree = tree;
	}
}
