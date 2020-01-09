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
 * This listener is responsible for generating the window to create a directory
 * @author Daniel Cuenca
 *
 */
public class CreateDirectoryListener implements ActionListener {

	private MainRoyalView royal;
	private FTPClient client;
	
	/**
	 * Initializes class variables
	 * 
	 * @param mainRoyalView - Main View Object 
	 * @param ftpClient - FTPClient - Connection FTP object
	 */
	public CreateDirectoryListener(MainRoyalView mainRoyalView, FTPClient ftpClient) {
		this.client = ftpClient;
		this.royal = mainRoyalView;
	}
	/**
	 * Verify that a directory has been selected and create the directory creation window
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		DataModel data = new DataModel();
		if (DataModel.actualUserPath != "") {
			if (!AuxiliaryTools.checkFile(client)) {
				if (FTPReply.isPositiveCompletion(client.getReplyCode())) {
					CreateModifyView view = new CreateModifyView(data.getCreateFolderTag(),
							data.getInsertNewDirLbl(), data.getCreateFolderTag(), 0, client, royal);
					view.setVisible(true);
				}
			} else {
				royal.getTxtaHistorial().append(data.getIsDirMsg() + "\n");
			}
		} else {
			royal.getTxtaHistorial().append(data.getSelectDirFirst()+ "\n");
		}

	}

}
