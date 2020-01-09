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
 * Listener for Patients button which changes tree to patients files
 * @author Daniel Cuenca
 *
 */
public class PatientsListener implements ActionListener {

	private FTPClient client;
	private MainRoyalView royal;
/**
 * Initializes class variables
 * 
 * @param ftpClient - FTPClient - Connection FTP object
 * @param mainRoyalView - MainRoyalView - Main view object
 */
	public PatientsListener(FTPClient ftpClient, MainRoyalView mainRoyalView) {
		this.client = ftpClient;
		this.royal = mainRoyalView;
	}
	/**
	 * change the working path of the FTP server and create the JTree with the files in it
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			client.changeWorkingDirectory("/Pacientes");
			royal.getRaiz().setUserObject("/Pacientes");
		} catch (IOException e1) {

		}

		DefaultMutableTreeNode root = royal.getRaiz();
		root.removeAllChildren();

		try {
			royal.createFilesTree(root, client.listFiles(), client);
			client.changeWorkingDirectory("/Pacientes");
		} catch (IOException e1) {

		}
		royal.changedJTree(root);

	}
}
