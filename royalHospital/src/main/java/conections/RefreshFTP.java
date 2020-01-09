package conections;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class RefreshFTP extends Thread{
	
	private FTPClient client;
	private Boolean flag = true;
	
	/**
	 * This function refresh conection with server FTP.
	 * 
	 * @param client
	 */
	public RefreshFTP(FTPClient client) {
		this.client = client;
	}

	@Override
	public void run() {
		do {
			try {
				client.sendNoOp();//Send a command to the server indicating that it is still connected
//				System.out.println("Refresca el Filezilla");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}while(flag);
	}
	
	/**
	 * This method closes the thread by breaking the while loop by changing the boolean to false
	 */
	public void stopRunning() {
			flag = false;	
	}

}
