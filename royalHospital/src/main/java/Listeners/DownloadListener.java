package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

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
		String path = "/";
		try {
			ftpClient.changeWorkingDirectory(path);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("C:\\Users\\Cristina\\Documents\\Proyecto Imagine Dev"));
			if(ftpClient.retrieveFile("royalhistorial.txt", output)) {
				mainRoyal.getTxtaHistorial().append("Se descargó el fichero con éxito");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
