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
 * Listener for rename button which opens the "insert new name" dialog
 * @author Cristina Montilla 
 *
 */
public class RenameListener implements ActionListener {

	private MainRoyalView royal;
	private FTPClient ftp;
	DataModel data = new DataModel();
/**
 * Initializes values of variables
 * @param MainRoyalView mainRoyalView Principal Frame
 * @param FTPCLient ftpClient FTP client
 */
	public RenameListener(MainRoyalView mainRoyalView, FTPClient ftpClient) {
		this.ftp = ftpClient;
		this.royal = mainRoyalView;
	}
/**
 * Function which opens the "insert new name" dialog
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (DataModel.actualUserPath != "") {
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				CreateModifyView view = new CreateModifyView(data.getRenameFileTag(), data.getInsertNewNameLbl(), data.getRenameTag(), 2, ftp, royal);
				view.setVisible(true);
			}
		} else {
			royal.getTxtaHistorial().append(data.getSelectFileOrDir() + "\n");
		}
	}

}
