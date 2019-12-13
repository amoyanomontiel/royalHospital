package conections;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class FTPConection {
	private static String ftpServer = "localhost";
	private static int port = 9000;
	private String ftpUser = "";
	private String ftpPassword = "";

	public FTPConection(String ftpUser, String ftpPassword) {
		this.ftpUser = ftpUser;
		this.ftpPassword = ftpPassword;
	}

	public FTPClient createFTPClient() {
		FTPClient client = new FTPClient();
		try {
			client.connect(ftpServer, port);
			if (client.login(ftpUser, ftpPassword)) {
				// consola.append("Conectado con el servidor\n");
				System.out.println("Conectado con el servidor\n");
			} else {
				// consola.append("No te has podido conectar, inicia sesión primero\n");
				System.out.println("No te has podido conectar, inicia sesión primero\n");
			}
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return client;
	}
}
