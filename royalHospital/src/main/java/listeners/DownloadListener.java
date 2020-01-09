package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.AuxiliaryTools;
import com.royalhospital.royalHospital.DataModel;

import views.ErrorRoyalView;
import views.MainRoyalView;

/**
 * Download button listener class which download a file
 * 
 * @author Cristina Montilla
 *
 */
public class DownloadListener implements ActionListener {

	MainRoyalView mainRoyal;
	FTPClient ftpClient;

	/**
	 * Initializes values of variables
	 * 
	 * @param MainRoyalView mainRoyal Principal frame
	 * @param FTPClient ftpClient FTPClient
	 */
	public DownloadListener(MainRoyalView mainRoyal, FTPClient ftpClient) {
		this.mainRoyal = mainRoyal;
		this.ftpClient = ftpClient;
	}
/**
 * Button event which checks if files exists and download it at user Download directory
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		DataModel data = new DataModel();
		if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
			if (DataModel.selectedFile != "") {
				if (!AuxiliaryTools.checkDirectory(ftpClient)) {
					try {
						ftpClient.changeWorkingDirectory(DataModel.directionPath);
						String sDirectoryWork = System.getProperty("user.home") + "/Downloads/";
						File downloadDirectory = new File(sDirectoryWork + DataModel.selectedFile);
						if (downloadDirectory.exists()) {
							mainRoyal.getTxtaHistorial().append(data.getDownLoadExits() + "\n");
						} else {
							FileOutputStream out = new FileOutputStream(sDirectoryWork + DataModel.selectedFile);
							if (ftpClient.retrieveFile(DataModel.selectedFile, out)) {
								AuxiliaryTools.saveOperationAtDBRecord(DataModel.codActualUser, "descargar",
										DataModel.selectedFile, AuxiliaryTools.actualDate(),
										AuxiliaryTools.actualTime());
								mainRoyal.getTxtaHistorial().append(data.getDownLoadSuccess() + "\n");
								mainRoyal.rootsToBlank();
							} else {
								mainRoyal.getTxtaHistorial().append(data.getDownLoadFail() + "\n");
							}
							out.close();
						}
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
						ErrorRoyalView error = new ErrorRoyalView(data.getFtpConectionError(), 0);
						error.setVisible(true);
						error.setLocationRelativeTo(null);
					}
				} else {
					mainRoyal.getTxtaHistorial().append(data.getDownLoadDirError() + "\n");
				}
			} else {
				mainRoyal.getTxtaHistorial().append(data.getSelectFileFirst() + "\n");
			}
		}
	}
}
