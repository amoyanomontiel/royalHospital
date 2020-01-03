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
			royal.getRaiz().setUserObject("\\" + user);
		} catch (IOException e1) {

		}

		DefaultMutableTreeNode raiz = royal.getRaiz();
		raiz.removeAllChildren();

		try {
			seekFile(raiz, ftpClient.listFiles(), ftpClient);
			ftpClient.changeWorkingDirectory("/Medicos/" + user);
		} catch (IOException e1) {

		}
		royal.changedJTree(raiz);

	}

	private void seekFile(DefaultMutableTreeNode raiz2, FTPFile[] files, FTPClient ftpClient) {
		FTPFile[] list = files;
		if (list != null)
			for (FTPFile fil : list) {
				if (fil.isDirectory()) {
					DefaultMutableTreeNode directory = new DefaultMutableTreeNode(fil.getName());
					raiz2.add(directory);

					try {
						ftpClient.changeWorkingDirectory(fil.getName());
						FTPFile[] list2 = ftpClient.listFiles();
						seekFile(directory, list2, ftpClient);
					} catch (IOException e) {
						ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar con el servidor FTP", 0);
						error.setVisible(true);
						error.setLocationRelativeTo(null);
					}
				} else {
					DefaultMutableTreeNode file = new DefaultMutableTreeNode(fil.getName());
					raiz2.add(file);
				}
			}
		try {
			ftpClient.changeToParentDirectory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
