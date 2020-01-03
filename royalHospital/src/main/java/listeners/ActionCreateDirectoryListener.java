package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.DataModel;

import views.ErrorRoyalView;
import views.MainRoyalView;

public class ActionCreateDirectoryListener implements ActionListener{
	
	private FTPClient ftp;
	private String directoryName;
	private MainRoyalView royal;

	public ActionCreateDirectoryListener(FTPClient ftpClient, String directoryName, MainRoyalView royalView) {
		this.ftp = ftpClient;
		this.directoryName = directoryName;
		this.royal = royalView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				FTPFile[] directory = ftp.listDirectories(DataModel.actualUserPath);
				System.out.println(DataModel.actualUserPath);
				for(FTPFile a: directory) {
					if(a.getName().equalsIgnoreCase(directoryName)) {
						royal.getTxtaHistorial().append("El directorio ya existe\n");
					}else {
						ftp.makeDirectory(DataModel.actualUserPath + "\\"+ directoryName);
						royal.getTxtaHistorial().append("El directorio '"+ directoryName +"' ha sido creado\n");
					}
				}
			}
			
		} catch (IOException e1) {
			ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar con el servidor FTP", 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
		
	}

}
