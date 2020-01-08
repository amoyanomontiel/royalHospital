package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.DataModel;

import conections.FTPConection;
import views.ErrorRoyalView;
import views.MainRoyalView;

/**
 * Listener class for remove file button
 * 
 * @author Cristina Montilla
 *
 */
public class RemoveListener implements ActionListener {

	MainRoyalView mainRoyal;
	FTPClient ftpClient;

	/**
	 * Initialize the objects
	 * 
	 * @param MainRoyalView mainRoyal Principal frame
	 * @param FTPClient     ftpClient Client ftp object
	 */
	public RemoveListener(MainRoyalView mainRoyal, FTPClient ftpClient) {
		this.mainRoyal = mainRoyal;
		this.ftpClient = ftpClient;
	}

	/**
	 * Click event function which takes selected File of tree and deletes it if its
	 * possible
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (DataModel.selectedFile != "") {
			// ftpClient.enterLocalPassiveMode();
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				try {
					ftpClient.changeWorkingDirectory(DataModel.directionPath);
					FTPFile fileForRemove = ftpClient.mlistFile(DataModel.actualUserPath);
					if (fileForRemove.isDirectory()) {
						FTPFile[] filesInside = ftpClient.listFiles(DataModel.actualUserPath);
						if (filesInside.length > 0) {
							mainRoyal.getTxtaHistorial()
									.append("No es posible borrar el directorio si contiene algún elemento\n");
						} else {
							if (FTPReply.isPositiveCompletion(ftpClient.rmd(DataModel.selectedFile))) {
								mainRoyal.getTxtaHistorial().append("Se borró el directorio con éxito\n");
								upDateTree();
								mainRoyal.rootsToBlank();
							} else {
								mainRoyal.getTxtaHistorial().append("No se pudo borrar el directorio\n");
							}
						}
					} else {
						if (ftpClient.deleteFile(DataModel.selectedFile)) {
							mainRoyal.getTxtaHistorial().append("Se borró el fichero con éxito\n");
							upDateTree();
							mainRoyal.rootsToBlank();
						} else {
							mainRoyal.getTxtaHistorial().append("No se pudo borrar el fichero\n");
						}
					}
				} catch (IOException ex) {
					ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar con el servidor FTP", 0);
					error.setVisible(true);
					error.setLocationRelativeTo(null);
				}
			} else {
				mainRoyal.getTxtaHistorial()
						.append("No se pudo borrar el archivo verifica que estás conectado al servidor\n");
			}
		} else {
			mainRoyal.getTxtaHistorial().append("Seleccione primero un fichero de la lista\n");
		}
	}

	private void upDateTree() {
		DefaultTreeModel model = (DefaultTreeModel) mainRoyal.getTree().getModel();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) mainRoyal.getTree().getLastSelectedPathComponent();
		model.removeNodeFromParent(node);
	}
}
