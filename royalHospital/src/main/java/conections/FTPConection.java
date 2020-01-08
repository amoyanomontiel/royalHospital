package conections;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import com.royalhospital.royalHospital.DataModel;

import views.ErrorRoyalView;

/**
 * FTP conection class. Creates the FTP client and conect it with server
 * 
 * @author Cristina Montilla / Daniel Cuenca
 *
 */
public class FTPConection {

	private static FTPClient client;
	private static Thread refresh;

	public FTPConection() {

	}

	/**
	 * This function creates the FTPClient object and conect it with the server.
	 * Then login with the user and password info.
	 * 
	 * @return FTPClient The FTP client object already conected
	 */
	public static FTPClient createFTPClient() {
		DataModel data = new DataModel();
		client = new FTPClient();
		try {
			client.connect(data.getFtpServer(), data.getPort());
			if (client.login(data.getFtpUser(), data.getFtpPassword())) {

				refresh = new Thread(new RefreshFTP(client));
				refresh.start();

			} else {
				ErrorRoyalView error = new ErrorRoyalView(data.getFtpConectionError(), 0);
				error.setVisible(true);
				error.setLocationRelativeTo(null);
			}
		} catch (Exception ex) {
			ErrorRoyalView error = new ErrorRoyalView(data.getFtpConectionError(), 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}

		return client;
	}

	public static void disconnectFTP() {
		try {
			new RefreshFTP().stopRunning();
			client.logout();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
