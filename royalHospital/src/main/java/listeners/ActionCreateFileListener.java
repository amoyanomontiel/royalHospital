package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.AuxiliaryTools;
import com.royalhospital.royalHospital.DataModel;

import views.CreateModifyView;
import views.ErrorRoyalView;
import views.MainRoyalView;
/**
 * 
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
		try {
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				ftp.changeWorkingDirectory(DataModel.actualUserPath);
//				String pathHome = System.getProperty("user.home") + "/downloads/";
				if(text.getText().toString().isEmpty() || text.getText().length()>20) {
					ErrorRoyalView error = new ErrorRoyalView("Escriba el nombre del fichero\n(máximo 20 caracteres) y su extensión si lo desea.", 1);
					error.setVisible(true);
					error.setLocationRelativeTo(null);
				}else {
					String[] splitName = text.getText().toString().split(Pattern.quote("."));
					String nameFile = "";
					File fileTmp = null;
					if(splitName.length>1) {
						fileTmp = File.createTempFile(splitName[0], "."+splitName[1]);
						nameFile = splitName[0] + "."+splitName[1];
					}else {
						fileTmp = File.createTempFile(splitName[0], ".txt");//Aqui peta a veces
						nameFile = splitName[0]+".txt";
					}
					FileInputStream input = new FileInputStream(fileTmp);
					ftp.storeFile(nameFile, input);
					nameFrame.dispose();
					AuxiliaryTools.saveOperationAtDBRecord(DataModel.codActualUser, "crear fichero", DataModel.selectedFile, 
							AuxiliaryTools.actualDate(), AuxiliaryTools.actualTime());
					royal.getTxtaHistorial().append("El fichero '"+ nameFile + "' ha sido creado\n");
					input.close();
//					fileTmp.deleteOnExit();
					fileTmp.delete();
					System.out.println(fileTmp.getAbsolutePath());
					
					royal.refreshJTree(nameFile);
					royal.rootsToBlank();
				}
			}
		} catch (IOException e1) {
			ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar con el servidor FTP", 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
	}

}
