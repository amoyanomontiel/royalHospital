package com.royalhospital.royalHospital;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;;

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
	private Folder[] folderList;

	private Folder emailFolder;

	private Message[] messages;

	//private ArrayList<ObjectEmail> listAllObjectsMail = new ArrayList<ObjectEmail>();

	public void storeAllMessages() {
		try {
			for (int counter = 0; counter < messages.length; counter++) {
				// Get message one by one
				// Check web page
				Message objectMessage = messages[counter];
				// Revisar pagina web para los archivos adjuntos
				//ObjectEmail objectM = new ObjectEmail(objectMessage.getSubject(), objectMessage.getFrom()[0].toString(), getBodyText(objectMessage));
				JEditorPane editor = new JEditorPane("text/html", getBodyText(objectMessage));
				System.out.println(getBodyText(objectMessage));
        	    editor.setEditable(false);
        	    editor.addHyperlinkListener(new HyperlinkListener() {
					
					@Override
					public void hyperlinkUpdate(HyperlinkEvent e) {
						if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
							if(Desktop.isDesktopSupported()) {
							    try {
									Desktop.getDesktop().browse(e.getURL().toURI());
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (URISyntaxException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
					        }
					}
				});
        	    JScrollPane pane = new JScrollPane(editor);
        	    JFrame f = new JFrame("HTML Demo");
        	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	    f.getContentPane().add(pane);
        	    f.setSize(800, 600);
        	    f.setVisible(true);
        	    //break;
			}	
		} catch (Exception e) {
			System.out.println("Error reading content about messages");
		}
	}

	public String getBodyText(Part bodyPart) {
		try {
			if (bodyPart.isMimeType("text/*")) {
				String contextBody = (String) bodyPart.getContent();
				return contextBody;
			}

			if (bodyPart.isMimeType("multipart/alternative")) {
				Multipart multiPart = (Multipart) bodyPart.getContent();
				String text = null;
				for (int i = 0; i < multiPart.getCount(); i++) {
					Part bodyP = multiPart.getBodyPart(i);
					if (bodyP.isMimeType("text/plain")) {
						if (text == null)
							text = getBodyText(bodyP);
						continue;
					} else if (bodyP.isMimeType("text/html")) {
						String textHtml = getBodyText(bodyP);
						if (textHtml != null)
							return textHtml;
					} else {
						return getBodyText(bodyP);
					}
				}
				return text;
			} else if (bodyPart.isMimeType("multipart/*")) {
				Multipart mp = (Multipart) bodyPart.getContent();
				for (int i = 0; i < mp.getCount(); i++) {
					String bodyText = getBodyText(mp.getBodyPart(i));
					if (bodyText != null)
						return bodyText;
				}
			}

			return null;
		} catch (Exception e) {
			System.out.println("Error getting body part about Message");
			return null;
		}
	}

	public void receiveAllEmails() {
		try {
			messages = emailFolder.getMessages();
			System.out.println("you have " + messages.length + " messages");
		} catch (Exception e) {
			System.out.println("Error getting messages");
		}
	}

	public void setFolderEmails() {
		try {
			folderList = store.getDefaultFolder().list();

			// Check folder --> "INBOX", why?
			emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);
		} catch (Exception e) {
			System.out.println("Error receive list of folders Email");
		}

	}

	public boolean connectMailServer() {
		try {
			// check getStore values --> "imaps", why?
			store = session.getStore("imaps");
			store.connect(host, userName, passwd);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public void setAllDataConnection(String hostParam, String mailStoreTypeParam, String userNameParam,
			String passwdParam) {
		host = hostParam;
		mailStoreType = mailStoreTypeParam;
		userName = userNameParam;
		passwd = passwdParam;
	}

	public void setProperties() {
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
