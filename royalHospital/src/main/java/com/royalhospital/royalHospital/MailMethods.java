package com.royalhospital.royalHospital;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;;

public class MailMethods {
	private String host;
	private String mailStoreType;
	private String userName;
	private String passwd;
	
	// All properties about connection with E-mail server
	private Properties properties = new Properties();
	
	// Session with E-mail server
	private Session session;
	
	// POP3 object that is used for this connection with Server
	private Store store;
	
	// List of all folders in E-mail
	private Folder [] folderList;
	
	private Folder emailFolder;
	
	private void receiveAllEmails() {
		
	}
	
	
	private void setFolderEmails() {
		try {
			folderList = store.getDefaultFolder().list();
			
			// Check folder --> "INBOX", why?
			emailFolder = store.getFolder("INBOX");
		} catch (Exception e) {
			System.out.println("Error receive list of folders Email");
		}
		
	}
	
	private boolean connectMailServer() {
		try {
			// check getStore values --> "imaps", why?
			store = session.getStore("imaps");
			store.connect(host, userName, passwd);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	private void setAllDataConnection(String hostParam, String mailStoreTypeParam, String userNameParam, String passwdParam) {
	host = hostParam;
	mailStoreType = mailStoreTypeParam;
	userName = userNameParam;
	passwd = passwdParam;
	}
	
	private void setProperties() {
		try {
			properties.put("mail.pop3.host", host);
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");	
			session = Session.getDefaultInstance(properties);
		} catch (Exception e) {
			System.out.println("Error setting properties about E-mail");
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getMailStoreType() {
		return mailStoreType;
	}

	public void setMailStoreType(String mailStoreType) {
		this.mailStoreType = mailStoreType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
