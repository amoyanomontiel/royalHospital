package conections;

import org.apache.commons.net.ftp.FTPClient;

import views.ErrorRoyalView;

public class FTPConection {
	private static String ftpServer = "localhost";
	private static int port = 9000;
	private final String ftpUser = "usuario";
	private final String ftpPassword = "usuario";

//	public FTPConection(String ftpUser, String ftpPassword) {
//		this.ftpUser = ftpUser;
//		this.ftpPassword = ftpPassword;
//	}

	public FTPConection() {
		
	}
	
	public FTPClient createFTPClient() {
		FTPClient client = new FTPClient();
		try {
			client.connect(ftpServer, port);
			if (client.login(ftpUser, ftpPassword)) {
											
			} else {
				ErrorRoyalView error = new ErrorRoyalView("La conexión con el servidor de Royal Hospital ha fallado", 0);
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
