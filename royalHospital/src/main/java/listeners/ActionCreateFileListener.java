package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.DataModel;

import views.ErrorRoyalView;
import views.MainRoyalView;

public class ActionCreateFileListener implements ActionListener{

	private FTPClient ftp;
	private JTextField text;
	private MainRoyalView royal;
	
	public ActionCreateFileListener(FTPClient ftpClient, JTextField textField, MainRoyalView royalView) {
		this.ftp = ftpClient;
		this.royal = royalView;
		this.text = textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				ftp.changeWorkingDirectory(DataModel.actualUserPath);
				FTPFile file = new FTPFile();
				file.setName(text.getText().toString());
				royal.getTxtaHistorial().append("El archivo '"+text.getText().toString() + "' ha sido creado");
			}
		} catch (IOException e1) {
			ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar con el servidor FTP", 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
			e1.printStackTrace();
		}
	}

}
