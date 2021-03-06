package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JFileChooser;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.AuxiliaryTools;
import com.royalhospital.royalHospital.DataModel;

import views.ErrorRoyalView;
import views.MainRoyalView;
/**
 * Class for Upload files function
 * @author Cristina Montilla
 *
 */
public class UploadListener implements ActionListener {

	MainRoyalView mainRoyal;
	FTPClient ftpClient;
	DataModel data = new DataModel();
	
/**
 * Initializes class variables
 * @param MainRoyalView mainRoyal Principal frame
 * @param FTPClient ftpClient Ftp client
 */
	public UploadListener(MainRoyalView mainRoyal, FTPClient ftpClient) {
		this.mainRoyal = mainRoyal;
		this.ftpClient = ftpClient;
	}
/**
 * Upload event function which take a file from user system and upload it to ftp server 
 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (DataModel.actualUserPath != "") {
			if(!AuxiliaryTools.checkFile(ftpClient)) {
				if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
					JFileChooser chooserFrame = new JFileChooser();
					chooserFrame.setFileSelectionMode(JFileChooser.FILES_ONLY);
					chooserFrame.setDialogTitle(data.getUploadFileTag());
					int selection = chooserFrame.showDialog(chooserFrame, data.getUploadTag());

					if (selection == JFileChooser.APPROVE_OPTION) {
						File file = chooserFrame.getSelectedFile();
						if (file != null) {
							try {
								ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
								ftpClient.changeWorkingDirectory(DataModel.actualUserPath);
								FileInputStream input = new FileInputStream(file);
								FTPFile[] files = ftpClient.listFiles();
								boolean exist = false;
								if (files != null) {
									System.out.println(file.getName());
									for (FTPFile f : files) {
										System.out.println(f.getName());
										if (f.getName().equals(file.getName())) {
											exist = true;
											break;
										}
										System.out.println(exist);
									}
								}
								if (exist) {
									mainRoyal.getTxtaHistorial().append(data.getFileAlreadyExist() + "\n");
								} else {
									if (ftpClient.storeFile(file.getName(), input)) {										
										AuxiliaryTools.saveOperationAtDBRecord(DataModel.codActualUser, data.getUploadTag(), file.getName(), 
												AuxiliaryTools.actualDate(), AuxiliaryTools.actualTime());
										mainRoyal.getTxtaHistorial().append(data.getFileUpload() + "\n");
										mainRoyal.refreshJTree(file.getName());
										mainRoyal.rootsToBlank();
									} else {
										mainRoyal.getTxtaHistorial().append(data.getUploadFake() + "\n");
									}
								}
								input.close();
							} catch (IOException ex) {
								ErrorRoyalView error = new ErrorRoyalView(data.getFtpConectionError(), 0);
								error.setVisible(true);
								error.setLocationRelativeTo(null);
							}
						}
					}
				}
			}else {
				mainRoyal.getTxtaHistorial().append(data.getIsFileMsg() + "\n");
			}			
		} else {
			mainRoyal.getTxtaHistorial().append(data.getSelectFileForUpload() + "\n");
		}
	}
}
