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
		String path = "/Medico/cesar";
		try {
			ftpClient.changeWorkingDirectory(path);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			//File downloadFile1 = new File("C:\\peval2");
            FileOutputStream outputStream1 = new FileOutputStream("C:\\peval2/" + selectionPath);
            boolean success = ftpClient.retrieveFile(selectionPath, outputStream1);
            outputStream1.close();
            if(success) {
            	System.out.println("succes");
            }
//			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(""));
			System.out.println("entra");
//			if(ftpClient.retrieveFile(selectionPath, output)) {
//				mainRoyal.getTxtaHistorial().append("Se descargó el fichero con éxito");
//			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
