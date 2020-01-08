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
 * 
 * @author Daniel Cuenca
 *
 */
public class PatientsListener implements ActionListener {

	private FTPClient client;
	private MainRoyalView royal;

	public PatientsListener(FTPClient ftpClient, MainRoyalView mainRoyalView) {
		this.client = ftpClient;
		this.royal = mainRoyalView;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			client.changeWorkingDirectory("/Pacientes");
			royal.getRaiz().setUserObject("/Pacientes");
		} catch (IOException e1) {

		}

		DefaultMutableTreeNode raiz = royal.getRaiz();
		raiz.removeAllChildren();

		try {
			royal.seekFile(raiz, client.listFiles(), client);
			client.changeWorkingDirectory("/Pacientes");
		} catch (IOException e1) {

		}
		royal.changedJTree(raiz);

	}
}
