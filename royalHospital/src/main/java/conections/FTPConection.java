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
	private static RefreshFTP refresh;

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

				refresh = new RefreshFTP(client);
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
	
	/**
	 * This method closes the connection with the FTP server and the thread refresh connection.
	 */
	public static void disconnectFTP() {
		try {
			client.logout();
			refresh.stopRunning();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
