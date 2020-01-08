package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.AuxiliaryTools;
import com.royalhospital.royalHospital.DataModel;

import views.CreateModifyView;
import views.MainRoyalView;
/**
 * 
 * @author Daniel Cuenca
 *
 */
public class CreateFileListener implements ActionListener{
	
	private MainRoyalView royal;
	private FTPClient ftp;
	
	
	public CreateFileListener(MainRoyalView mainRoyalView, FTPClient ftpClient) {
		this.ftp = ftpClient;
		this.royal = mainRoyalView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (DataModel.actualUserPath != "") {
			if(!AuxiliaryTools.checkFile(ftp)) {
				if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
					CreateModifyView view = new CreateModifyView("Crear Fichero", "Introduce el nombre del fichero: ", "Crear Fichero", 1, ftp, royal);
					view.setVisible(true);
					view.setLocationRelativeTo(null);
				}
			}else {
				royal.getTxtaHistorial().append("Para crear un fichero es necesario seleccionar un directorio de creación" + "\n");
			}
			
		} else {
			royal.getTxtaHistorial().append("Seleccione primero una carpeta donde crear el fichero\n");
		}
		
	}

}
