package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.DataModel;

import conections.FTPConection;
import views.ErrorRoyalView;
import views.MainRoyalView;

public class RemoveListener implements ActionListener {

	MainRoyalView mainRoyal;
	FTPClient ftpClient;

	public RemoveListener(MainRoyalView mainRoyal, FTPClient ftpClient) {
		this.mainRoyal = mainRoyal;
		this.ftpClient = ftpClient;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (DataModel.selectedFile != "") {
			//ftpClient.enterLocalPassiveMode();
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				try {
					ftpClient.changeWorkingDirectory(DataModel.directionPath);
					if (ftpClient.deleteFile(DataModel.selectedFile)) {
						mainRoyal.getTxtaHistorial().append("Se borró el archivo satisfactoriamente\n");
					} else {
						mainRoyal.getTxtaHistorial().append("No se pudo borrar el archivo\n");
					}
				} catch (IOException ex) {
					ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar con el servidor FTP", 0);
					error.setVisible(true);
					error.setLocationRelativeTo(null);
				}
			} else {
				mainRoyal.getTxtaHistorial()
						.append("No se pudo borrar el archivo verifica que estás conectado al servidor\n");
			}
		} else {
			mainRoyal.getTxtaHistorial().append("Seleccione primero un fichero de la lista\n");
		}

	}
}
