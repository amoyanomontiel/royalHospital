package conections;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class RefreshFTP extends Thread{
	
	private FTPClient client;
	private Boolean flag = true;
	
	public RefreshFTP(FTPClient client) {
		this.client = client;
	}

	@Override
	public void run() {
		do {
			try {
				client.sendNoOp();
//				System.out.println("Refresca el Filezilla");
			} catch (IOException e) {
				System.out.println("oh");
			}
//			System.out.println(flag);
			
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(flag);
	}
	
	public void stopRunning() {
			flag = false;	
	}

}
