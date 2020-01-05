package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import com.royalhospital.royalHospital.DataModel;
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
		if (DataModel.actualUserPath != "") {
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
							ftpClient.changeWorkingDirectory(DataModel.actualUserPath);
							FileInputStream input = new FileInputStream(file);
							FTPFile[] files = ftpClient.listFiles();
							boolean exist = false;
							if (files != null) {
								System.out.println(file.getName());
								for (FTPFile f : files) {
									System.out.println(f.getName());
									if (f.getName().equals(file.getName())) {
										exist = true;
										break;
									}
									System.out.println(exist);
								}
							}
							if (exist) {
								mainRoyal.getTxtaHistorial().append("El fichero ya existe en el directorio actual\n");
							} else {
								if (ftpClient.storeFile(file.getName(), input)) {
									mainRoyal.getTxtaHistorial().append("Cargó satisfactoriamente el fichero\n");
									mainRoyal.refreshJTree(file.getName());
								} else {
									mainRoyal.getTxtaHistorial().append("No se pudo cargar el fichero\n");
								}
							}
							input.close();
						} catch (IOException ex) {
							// Error
						}
					}
					// Error
				}
			}
			// Error
		} else {
			mainRoyal.getTxtaHistorial().append("Seleccione primero un directorio donde cargar el fichero\n");
		}
	}
}
