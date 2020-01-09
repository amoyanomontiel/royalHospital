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
 * This listener is responsible for generating the window to create a file
 * 
 * @author Daniel Cuenca
 *
 */
public class CreateFileListener implements ActionListener{
	
	private MainRoyalView royal;
	private FTPClient ftp;
	
	/**
	 * Initializes class variables
	 * 
	 * @param mainRoyalView - MainRoyalView - Main view object
	 * @param ftpClient - FTPClient - Connection FTP object
	 */
	public CreateFileListener(MainRoyalView mainRoyalView, FTPClient ftpClient) {
		this.ftp = ftpClient;
		this.royal = mainRoyalView;
	}
	
	/**
	 * Verify that a directory has been selected and create the file creation window
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		DataModel data = new DataModel();
		if (DataModel.actualUserPath != "") {
			if(!AuxiliaryTools.checkFile(ftp)) {
				if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
					CreateModifyView view = new CreateModifyView(data.getCreateFileTag(), data.getInsertNewFileLbl(), data.getCreateFileTag(), 1, ftp, royal);
					view.setVisible(true);
				}
			}else {
				royal.getTxtaHistorial().append(data.getFileMsg() + "\n");
			}
			
		} else {
			royal.getTxtaHistorial().append(data.getSelectDir()+"\n");
		}
		
	}

}
