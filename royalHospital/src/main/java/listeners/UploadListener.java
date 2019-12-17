package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import views.MainRoyalView;

public class UploadListener implements ActionListener {

	MainRoyalView mainRoyal;
	FTPClient ftpClient;

	public UploadListener(MainRoyalView mainRoyal, FTPClient ftpClient) {
		this.mainRoyal = mainRoyal;
		this.ftpClient = ftpClient;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
			JFileChooser chooserFrame = new JFileChooser();
			chooserFrame.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooserFrame.setDialogTitle("Cargar fichero");
			int selection = chooserFrame.showDialog(chooserFrame, "Cargar");

			if (selection == JFileChooser.APPROVE_OPTION) {
				File file = chooserFrame.getSelectedFile();
				if (file != null) {

					try {
						ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
						ftpClient.changeWorkingDirectory("/");// Poner directorio actual abierto
						FileInputStream input = new FileInputStream(file);
						//Comprobar si el fichero ya existe
						if (ftpClient.storeFile(file.getName(), input)) {
							mainRoyal.getTxtaHistorial().append("Cargó satisfactoriamente el archivo\n");
						}
					} catch (IOException ex) {
						// Error
					}
				}
				//Error
			}
		}
		//Error
	}
}
