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
		try {
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				FTPFile[] directory = ftp.listDirectories(DataModel.actualUserPath);
				if(directory != null && directory.length != 0) {
					for (FTPFile a : directory) {
						if ((!directoryName.getText().toString().equalsIgnoreCase("")) && (directoryName.getText().length()<20)) {
							if (a.getName().equalsIgnoreCase(directoryName.getText().toString())) {
								royal.getTxtaHistorial().append("El directorio ya existe\n");
							} else {
								ftp.makeDirectory(DataModel.actualUserPath + "/" + directoryName.getText().toString());
								nameFrame.dispose();
								AuxiliaryTools.saveOperationAtDBRecord(DataModel.codActualUser, "crear directorio", DataModel.selectedFile, 
										AuxiliaryTools.actualDate(), AuxiliaryTools.actualTime());
								royal.getTxtaHistorial().append("El directorio '" + directoryName.getText().toString() + "' ha sido creado\n");
								royal.refreshJTree(directoryName.getText().toString());
								break;
							}
						}else {
							ErrorRoyalView error = new ErrorRoyalView("Escribe un nombre para el directorio de máximo 20 caracteres", 1);
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
						royal.getTxtaHistorial().append("El directorio '" + directoryName.getText().toString() + "' ha sido creado\n");
						royal.refreshJTree(directoryName.getText().toString());
						royal.rootsToBlank();
					}else {
						ErrorRoyalView error = new ErrorRoyalView("Escribe un nombre para el directorio", 1);
						error.setVisible(true);
						error.setLocationRelativeTo(null);
					}
				}
			}
		} catch (IOException e1) {
			ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar con el servidor FTP", 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
	}

}