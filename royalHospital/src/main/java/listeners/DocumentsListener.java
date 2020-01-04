package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import views.ErrorRoyalView;
import views.MainRoyalView;

public class DocumentsListener implements ActionListener {

	private String user;
	private FTPClient ftpClient;
	private MainRoyalView royal;

	public DocumentsListener(String user, FTPClient ftpClient, MainRoyalView mainRoyalView) {
		this.user = user;
		this.ftpClient = ftpClient;
		this.royal = mainRoyalView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			ftpClient.changeWorkingDirectory("/Medicos/" + user);
			royal.getRaiz().setUserObject("/Medicos/" + user);
		} catch (IOException e1) {

		}

		DefaultMutableTreeNode raiz = royal.getRaiz();
		raiz.removeAllChildren();

		try {
			royal.seekFile(raiz, ftpClient.listFiles(), ftpClient);
			ftpClient.changeWorkingDirectory("/Medicos/" + user);
		} catch (IOException e1) {

		}
		royal.changedJTree(raiz);

	}
}
