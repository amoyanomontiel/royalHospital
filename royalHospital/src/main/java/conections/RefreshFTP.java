package conections;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.net.ftp.FTPClient;

public class RefreshFTP implements Runnable{
	
	private FTPClient client;
	private ScheduledExecutorService event;
	
	public RefreshFTP(FTPClient client) {
		this.client = client;
		
		event = Executors.newSingleThreadScheduledExecutor();
		event.scheduleAtFixedRate(this, 1, 1, TimeUnit.MINUTES);
	}

	public RefreshFTP() {}

	@Override
	public void run() {
		try {
			client.sendNoOp();
			System.out.println("Refresca el Filezilla");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stopRunning() {
		event.shutdown();
	}

}
