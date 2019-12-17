package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
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
			if (DataModel.selectedPath != "") {
				try {
					ftpClient.changeWorkingDirectory(DataModel.actualUserPath);
				} catch (IOException e2) {
					//Error
				}
				try {
					String sDirectoryWork = System.getProperty("user.home") + "/Downloads/";
					System.out.println(sDirectoryWork);
					FileOutputStream out = new FileOutputStream(sDirectoryWork + DataModel.selectedPath);
					if (ftpClient.retrieveFile(DataModel.selectedPath, out)) {
						mainRoyal.getTxtaHistorial().append("Se descargó el fichero con éxito \n");
					} else {
						mainRoyal.getTxtaHistorial().append("No es posible descargar el fichero \n");
					}
				} catch (IOException e1) {
					// Error
				}
			} else {
				mainRoyal.getTxtaHistorial().append("Seleccione primero un fichero en la lista \n");
			}
		}
	}
}
