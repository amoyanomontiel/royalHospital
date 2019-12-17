package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
				String path = "/Medicos/cesar";
				try {
					ftpClient.changeWorkingDirectory(path);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					System.out.println(DataModel.selectedPath);
					FileOutputStream out = new FileOutputStream("C:\\peval3/" + DataModel.selectedPath);
					if (ftpClient.retrieveFile(DataModel.selectedPath, out)) {
						mainRoyal.getTxtaHistorial().append("Se descargó el fichero con éxito");
					} else {
						mainRoyal.getTxtaHistorial().append("No es posible descargar el fichero");
					}
				} catch (IOException e1) {
					// Error
					e1.printStackTrace();
				}
			} else {
				mainRoyal.getTxtaHistorial().append("Seleccione primero un fichero en la lista");
		String path = "/Medicos/Cesar";
		try {
			ftpClient.changeWorkingDirectory(path);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			System.out.println(DataModel.selectedPath);
			FileOutputStream out = new FileOutputStream("C:\\peval3/" + DataModel.selectedPath);
			if(ftpClient.retrieveFile(DataModel.selectedPath, out)) {
				mainRoyal.getTxtaHistorial().append("Se descargó el fichero con éxito");
			}
		}
	}
}
