package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JTextField;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.AuxiliaryTools;
import com.royalhospital.royalHospital.DataModel;

import views.CreateModifyView;
import views.ErrorRoyalView;
import views.MainRoyalView;
/**
 * Class to create files and update Jtree
 * @author Daniel Cuenca
 *
 */
public class ActionCreateFileListener implements ActionListener{

	private FTPClient ftp;
	private JTextField text;
	private MainRoyalView royal;
	private CreateModifyView nameFrame;
	
	public ActionCreateFileListener(FTPClient ftpClient, JTextField textField, MainRoyalView royalView, CreateModifyView nameFrame) {
		this.ftp = ftpClient;
		this.royal = royalView;
		this.text = textField;
		this.nameFrame = nameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DataModel data = new DataModel();
		try {
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				ftp.changeWorkingDirectory(DataModel.actualUserPath);
				if(text.getText().toString().isEmpty() || text.getText().length()>50) {
					ErrorRoyalView error = new ErrorRoyalView(data.getCreateFileTextLeght(), 1);
					error.setVisible(true);
					error.setLocationRelativeTo(null);
				}else {
					String fileExtension = FilenameUtils.getExtension(text.getText());
					String fileName = FilenameUtils.getBaseName(text.getText());
					File fileTmp = null;
					String nameFile = "";
					if(fileExtension.equalsIgnoreCase("")) {
						fileTmp = File.createTempFile(fileName, ".txt");
						nameFile = fileName + ".txt";
					}else {
						fileTmp = File.createTempFile(fileName, fileExtension);
						System.out.println(fileExtension);
						nameFile = fileName + "." + fileExtension;
					}
					FileInputStream input = new FileInputStream(fileTmp);
					ftp.storeFile(nameFile, input);
					nameFrame.dispose();
					AuxiliaryTools.saveOperationAtDBRecord(DataModel.codActualUser, "crear fichero", DataModel.selectedFile, 
							AuxiliaryTools.actualDate(), AuxiliaryTools.actualTime());
					royal.getTxtaHistorial().append(data.getTheFile() + nameFile + data.getCreatedSuccess() +"\n");
					input.close();
					fileTmp.delete();
					System.out.println(fileTmp.getAbsolutePath());
					
					royal.refreshJTree(nameFile);
					royal.rootsToBlank();
				}
			}
		} catch (IOException e1) {
			ErrorRoyalView error = new ErrorRoyalView(data.getFtpConectionError(), 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
	}

}
