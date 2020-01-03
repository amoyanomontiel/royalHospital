package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.DataModel;

import views.CreateModifyView;
import views.MainRoyalView;

public class CreateDirectoryListener implements ActionListener{
	
	private MainRoyalView royal;
	private FTPClient client;
	
	public CreateDirectoryListener(MainRoyalView mainRoyalView, FTPClient ftpClient) {
		this.client = ftpClient;
		this.royal = mainRoyalView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (DataModel.actualUserPath != "") {
			if (FTPReply.isPositiveCompletion(client.getReplyCode())) {
				CreateModifyView view = new CreateModifyView("Crear Directorio", "Introduce el nombre de directorio: ", "Crear Directorio", 0, client, royal);
				view.setVisible(true);
				view.setLocationRelativeTo(null);
			}
		} else {
			royal.getTxtaHistorial().append("Seleccione primero un directorio en donde crear la carpeta\n");
		}
		
	}

}
