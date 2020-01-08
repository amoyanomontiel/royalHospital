package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.AuxiliaryTools;
import com.royalhospital.royalHospital.DataModel;

import views.ErrorRoyalView;
import views.MainRoyalView;
/**
 * 
 * @author Cristina Montilla
 *
 */
public class DownloadListener implements ActionListener {

	MainRoyalView mainRoyal;
	FTPClient ftpClient;

	public DownloadListener(MainRoyalView mainRoyal, FTPClient ftpClient) {
		this.mainRoyal = mainRoyal;
		this.ftpClient = ftpClient;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
			if (DataModel.selectedFile != "") {
				if (!AuxiliaryTools.checkDirectory(ftpClient)) {
					try {
						ftpClient.changeWorkingDirectory(DataModel.directionPath);
						String sDirectoryWork = System.getProperty("user.home") + "/Downloads/";
						File downloadDirectory = new File(sDirectoryWork + DataModel.selectedFile);
						if (downloadDirectory.exists()) {
							mainRoyal.getTxtaHistorial()
									.append("El fichero ya está descargado, revise su directorio de descargas\n");
						} else {
							FileOutputStream out = new FileOutputStream(sDirectoryWork + DataModel.selectedFile);
							if (ftpClient.retrieveFile(DataModel.selectedFile, out)) {
								AuxiliaryTools.saveOperationAtDBRecord(DataModel.codActualUser, "descargar", DataModel.selectedFile, 
										AuxiliaryTools.actualDate(), AuxiliaryTools.actualTime());
								mainRoyal.getTxtaHistorial().append("Se descargó el fichero con éxito en su directorio de descargas \n");
							} else {
								mainRoyal.getTxtaHistorial().append("No se pudo descargar el fichero \n");
							}
							out.close();
						}
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
						ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar con el servidor FTP", 0);
						error.setVisible(true);
						error.setLocationRelativeTo(null);
					}
				} else {
					mainRoyal.getTxtaHistorial().append("No es posible descargar un directorio \n");
				}
			} else {
				mainRoyal.getTxtaHistorial().append("Seleccione primero un fichero en la lista \n");
			}
		}
	}
}
