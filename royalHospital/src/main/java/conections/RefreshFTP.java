package conections;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.net.ftp.FTPClient;

public class RefreshFTP implements Runnable{
	
	private FTPClient client;
	private ScheduledExecutorService event = Executors.newSingleThreadScheduledExecutor();
	private Boolean flag = true;
	
	public RefreshFTP(FTPClient client) {
		this.client = client;
		event.scheduleAtFixedRate(this, 1, 1, TimeUnit.MINUTES);
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
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void stopRunning() {
		if(!event.isShutdown()) {
			flag = false;
			event.shutdownNow();
			if(event.isTerminated()) {
				System.out.println("se ha parado loco");
			}
		}else {
			System.out.println("se ha parado");
		}
		
	}

}
