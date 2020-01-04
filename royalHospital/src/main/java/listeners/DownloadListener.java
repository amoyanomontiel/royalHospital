package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import com.royalhospital.royalHospital.DataModel;

import views.ErrorRoyalView;
import views.MainRoyalView;

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
				if (!checkDirectory()) {
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
								mainRoyal.getTxtaHistorial().append("Se descargó el fichero con éxito \n");
							} else {
								mainRoyal.getTxtaHistorial().append("No se pudo descargar el fichero \n");
							}
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

	private boolean checkDirectory() {
		boolean isDirectory = false;
		FTPFile file = null;
		try {
			file = ftpClient.mlistFile(DataModel.actualUserPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (file.isDirectory()) {
			isDirectory = true;
		}
		return isDirectory;
	}
}
