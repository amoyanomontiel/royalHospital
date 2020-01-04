package conections;

import org.apache.commons.net.ftp.FTPClient;

import views.ErrorRoyalView;

/**
 * FTP conection class. Creates the FTP client and conect it with server
 * Server
 * 
 * @author Cristina Montilla / Daniel Cuenca
 *
 */
public class FTPConection {
	/**
	 * Class variables with conection info
	 */
	private static String ftpServer = "localhost";
	private static int port = 9000;
	private final static String ftpUser = "usuario";
	private final static String ftpPassword = "usuario";

	/**
	 * Create the object
	 */
	public FTPConection() {

	}

	/**
	 * This function creates the FTPClient object and conect it with the server.
	 * Then login with the user and password info.
	 * 
	 * @return FTPClient The FTP client object already conected
	 */
	public static FTPClient createFTPClient() {
		FTPClient client = new FTPClient();
		try {
			client.connect(ftpServer, port);
			if (client.login(ftpUser, ftpPassword)) {

			} else {
				ErrorRoyalView error = new ErrorRoyalView("La conexión con el servidor de Royal Hospital ha fallado",
						0);
				error.setVisible(true);
				error.setLocationRelativeTo(null);
			}
		} catch (Exception ex) {
			ErrorRoyalView error = new ErrorRoyalView("La conexión con el servidor de Royal Hospital ha fallado", 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}

		return client;
	}
}
