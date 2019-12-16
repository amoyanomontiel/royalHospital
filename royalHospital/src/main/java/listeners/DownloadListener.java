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

import views.MainRoyalView;

public class DownloadListener implements ActionListener {

	MainRoyalView mainRoyal;
	FTPClient ftpClient;
	String selectionPath;

	public DownloadListener(MainRoyalView mainRoyal, FTPClient ftpClient, String selectionPath) {
		this.mainRoyal = mainRoyal;
		this.ftpClient = ftpClient;
		this.selectionPath = selectionPath;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String path = "/Pacientes/Juan/";
		try {
			ftpClient.changeWorkingDirectory(path);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			FileOutputStream out = new FileOutputStream("D:\\peval3/Juan.txt");
			if(ftpClient.retrieveFile("Juan.txt", out)) {
				mainRoyal.getTxtaHistorial().append("Se descargó el fichero con éxito");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
