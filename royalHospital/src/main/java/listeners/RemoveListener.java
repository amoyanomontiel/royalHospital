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

import com.royalhospital.royalHospital.AuxiliaryTools;
import com.royalhospital.royalHospital.DataModel;

import conections.FTPConection;
import views.CreateModifyView;
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
	DataModel data = new DataModel();

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
							mainRoyal.getTxtaHistorial().append(data.getDeleteNoPossibleWithElems() + "\n");
						} else {
							CreateModifyView confirmDialog = new CreateModifyView(ftpClient, mainRoyal, "directorio");
							confirmDialog.setVisible(true);
						}
					} else {
						CreateModifyView confirmDialog = new CreateModifyView(ftpClient, mainRoyal, "fichero");
						confirmDialog.setVisible(true);
					}
				} catch (IOException ex) {
					ErrorRoyalView error = new ErrorRoyalView(data.getFtpConectionError(), 0);
					error.setVisible(true);
					error.setLocationRelativeTo(null);
				}
			} else {
				mainRoyal.getTxtaHistorial().append(data.getVerifyServer() + "\n");
			}
		} else {
			mainRoyal.getTxtaHistorial().append(data.getSelectFileFirst() + "\n");
		}
	}
}
