package conections;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class RefreshFTP implements Runnable{
	
	private FTPClient client;
	private Boolean flag = true;
	
	public RefreshFTP(FTPClient client) {
		this.client = client;
	}

	public RefreshFTP() {}

	@Override
	public void run() {
		while(flag) {
			try {
				client.sendNoOp();
				System.out.println("Refresca el Filezilla");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("he salido del while");
		System.out.println("El boolean es" + flag);
	}
	
	public void stopRunning() {
			flag = false;				
	}

}
