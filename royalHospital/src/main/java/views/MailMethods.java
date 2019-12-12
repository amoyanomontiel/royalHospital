package views;

import java.util.Properties;

import com.mysql.cj.xdevapi.Session;

public class MailMethods {
	private String host;
	private String mailStoreType;
	private String userName;
	private String passwd;
	
	// All properties about connection with E-mail server
	private Properties properties = new Properties();
	
	// Session with E-mail server
	private Session session;
	
	private void receiveAllEmails() {
		
	}
	
	private void setAllDataConnection(String hostParam, String mailStoreTypeParam, String userNameParam, String passwdParam) {
		
	}
	
	
}
