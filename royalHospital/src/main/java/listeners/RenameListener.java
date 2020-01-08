/**
 * 
 */
package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.DataModel;

import views.CreateModifyView;
import views.MainRoyalView;

/**
 * @author Cristina
 *
 */
public class RenameListener implements ActionListener {

	private MainRoyalView royal;
	private FTPClient ftp;
	/**
	 * 
	 */
	public RenameListener(MainRoyalView mainRoyalView, FTPClient ftpClient) {
		this.ftp = ftpClient;
		this.royal = mainRoyalView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Añadir comprobaciones
		if (DataModel.actualUserPath != "") {
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				CreateModifyView view = new CreateModifyView("Renombrar fichero", "Introduce el nuevo nombre del fichero: ", "Renombrar", 2, ftp, royal);
				view.setVisible(true);
				view.setLocationRelativeTo(null);
			}
		} else {
			royal.getTxtaHistorial().append("Seleccione primero un fichero o directorio de la lista\n");
		}
	}

}
