package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.AuxiliaryTools;
import com.royalhospital.royalHospital.DataModel;

import views.CreateModifyView;
import views.ErrorRoyalView;
import views.MainRoyalView;
/**
 * 
 * @author Daniel Cuenca
 *
 */
public class ActionCreateDirectoryListener implements ActionListener {

	private FTPClient ftp;
	private JTextField directoryName;
	private MainRoyalView royal;
	private CreateModifyView nameFrame;
	
	public ActionCreateDirectoryListener(FTPClient ftpClient, JTextField textField, MainRoyalView royalView, CreateModifyView nameframe) {
		this.ftp = ftpClient;
		this.directoryName = textField;
		this.royal = royalView;
		this.nameFrame = nameframe;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DataModel data = new DataModel();
		try {
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				FTPFile[] directory = ftp.listDirectories(DataModel.actualUserPath);
				if(directory != null && directory.length != 0) {
					for (FTPFile a : directory) {
						if ((!directoryName.getText().toString().equalsIgnoreCase("")) && (directoryName.getText().length()<50)) {
							if (a.getName().equalsIgnoreCase(directoryName.getText().toString())) {
								royal.getTxtaHistorial().append(data.getCreateDirNoPossible() + "\n");
							} else {
								ftp.makeDirectory(DataModel.actualUserPath + "/" + directoryName.getText().toString());
								nameFrame.dispose();
								AuxiliaryTools.saveOperationAtDBRecord(DataModel.codActualUser, "crear directorio", DataModel.selectedFile, 
										AuxiliaryTools.actualDate(), AuxiliaryTools.actualTime());
								royal.getTxtaHistorial().append(data.getTheDir() + directoryName.getText().toString() + data.getCreatedSuccess() +"\n");
								royal.refreshJTree(directoryName.getText().toString());
								break;
							}
						}else {
							ErrorRoyalView error = new ErrorRoyalView(data.getCreateDirLeghtMaxException(), 1);
							error.setVisible(true);
							error.setLocationRelativeTo(null);
							break;
						}
					}
				}else {
					if(!directoryName.getText().toString().equalsIgnoreCase("")) {
						ftp.makeDirectory(DataModel.actualUserPath + "/" + directoryName.getText().toString());
						nameFrame.dispose();
						AuxiliaryTools.saveOperationAtDBRecord(DataModel.codActualUser, "crear directorio", DataModel.selectedFile, 
								AuxiliaryTools.actualDate(), AuxiliaryTools.actualTime());
						royal.getTxtaHistorial().append(data.getTheDir() + directoryName.getText().toString() + data.getCreatedSuccess() + "\n");
						royal.refreshJTree(directoryName.getText().toString());
						royal.rootsToBlank();
					}else {
						ErrorRoyalView error = new ErrorRoyalView(data.getCreateDirTextEmpty(), 1);
						error.setVisible(true);
						error.setLocationRelativeTo(null);
					}
				}
			}
		} catch (IOException e1) {
			ErrorRoyalView error = new ErrorRoyalView(data.getFtpConectionError(), 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
	}

}
