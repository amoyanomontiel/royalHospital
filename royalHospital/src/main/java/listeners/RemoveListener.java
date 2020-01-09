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
							if (FTPReply.isPositiveCompletion(ftpClient.rmd(DataModel.selectedFile))) {
								AuxiliaryTools.saveOperationAtDBRecord(DataModel.codActualUser, data.getDeleteDirTag(),
										DataModel.selectedFile, AuxiliaryTools.actualDate(),
										AuxiliaryTools.actualTime());
								mainRoyal.getTxtaHistorial().append(data.getDeleteSuccess() + "\n");
								upDateTree();
								mainRoyal.rootsToBlank();
							} else {
								mainRoyal.getTxtaHistorial().append(data.getDeleteDirNoPossible() + "\n");
							}
						}
					} else {
						//Ventana de confirmacion
						if (ftpClient.deleteFile(DataModel.selectedFile)) {
							AuxiliaryTools.saveOperationAtDBRecord(DataModel.codActualUser, data.getDeleteFileTag(),
									DataModel.selectedFile, AuxiliaryTools.actualDate(), AuxiliaryTools.actualTime());
							mainRoyal.getTxtaHistorial().append(data.getDeleteFileSuccess() + "\n");
							upDateTree();
							mainRoyal.rootsToBlank();
						} else {
							mainRoyal.getTxtaHistorial().append(data.getDeleteFileNoPossible() + "\n");
						}
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

	/**
	 * Function which updates the files tree after delete
	 */
	private void upDateTree() {
		DefaultTreeModel model = (DefaultTreeModel) mainRoyal.getTree().getModel();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) mainRoyal.getTree().getLastSelectedPathComponent();
		model.removeNodeFromParent(node);
	}
}
