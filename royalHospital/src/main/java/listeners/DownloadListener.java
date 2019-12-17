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
			if(ftpClient.retrieveFile(DataModel.selectedPath, out)) {
				mainRoyal.getTxtaHistorial().append("Se descargó el fichero con éxito");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
