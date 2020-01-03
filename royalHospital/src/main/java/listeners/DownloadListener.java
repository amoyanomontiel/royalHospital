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
				try {
					ftpClient.changeWorkingDirectory(DataModel.directionPath);
				} catch (IOException e2) {
					//Error
				}
				try {
					String sDirectoryWork = System.getProperty("user.home") + "/Downloads/";
					FileOutputStream out = new FileOutputStream(sDirectoryWork + DataModel.selectedFile);
					if (ftpClient.retrieveFile(DataModel.selectedFile, out)) {
						mainRoyal.getTxtaHistorial().append("Se descargó el fichero con éxito \n");
					} else {
//						System.out.println(DataModel.actualUserPath);
//						FTPFile downFile = ftpClient.mlistFile(DataModel.actualUserPath);						
//						if(downFile.isDirectory()) {
//							mainRoyal.getTxtaHistorial().append("No es posible descargar un directorio \n");
//						}else {
//							mainRoyal.getTxtaHistorial().append("No es posible descargar el fichero \n");
//						}

					}
					out.close();
				} catch (IOException e1) {
					// Error
				}
			} else {
				mainRoyal.getTxtaHistorial().append("Seleccione primero un fichero en la lista \n");
			}
		}
	}
}
