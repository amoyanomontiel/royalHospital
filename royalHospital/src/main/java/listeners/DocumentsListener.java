package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import views.ErrorRoyalView;
import views.MainRoyalView;
/**
 * This listener shows the doctor's documents
 * 
 * @author Daniel Cuenca
 *
 */
public class DocumentsListener implements ActionListener {

	private String user;
	private FTPClient ftpClient;
	private MainRoyalView royal;
	
	/**
	 * Initializes class variables
	 * 
	 * @param user - String - username
	 * @param ftpClient - FTPClient - connection FTP object
	 * @param mainRoyalView - MainRoyalView - Main view object
	 */
	public DocumentsListener(String user, FTPClient ftpClient, MainRoyalView mainRoyalView) {
		this.user = user;
		this.ftpClient = ftpClient;
		this.royal = mainRoyalView;
	}
	
	/**
	 * change the working path of the FTP server and create the JTree with the files in it
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			ftpClient.changeWorkingDirectory("/Medicos/" + user);
			royal.getRaiz().setUserObject("/Medicos/" + user);
		} catch (IOException e1) {

		}

		DefaultMutableTreeNode root = royal.getRaiz();
		root.removeAllChildren();

		try {
			royal.createFilesTree(root, ftpClient.listFiles(), ftpClient);
			ftpClient.changeWorkingDirectory("/Medicos/" + user);
		} catch (IOException e1) {

		}
		royal.changedJTree(root);

	}
}
