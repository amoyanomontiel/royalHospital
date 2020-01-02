package com.royalhospital.royalHospital;

// All imports
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.lang3.StringUtils;

import Listeners.ScrollEmailListener;

/**
 * Contail all methods about connect and receive all messages
 * 
 * @author Javier
 * @version 1.0
 *
 */
public class MailMethods {

	/**
	 * Parameters necessary for to connect to Gmail
	 */
	private String host;
	private String mailStoreType;
	private String userName;
	private String passwd;

	// All properties about connection with E-mail server
	private Properties properties = new Properties();

	// Session with E-mail server
	private Session session;

	// POP3 object that is used for this connection with Server
	private static Store store;

	// List of all folders in E-mail
	private static Folder[] folderList;

	// Falta eliminar img del e-mail a la hora de abrirlo
	private static Folder emailFolder;

	// Scroll that containts all emails received
	private static JComboBox scrollEmails;

	// List of messages received
	private static Message[] messages;

	// View for to show scroll object
	private static JPanel viewScroll;

	// List of attachents that containts the (selected) email
	private static ArrayList<File> attachments;

	// Containts the home rute of user
	private static String homeRute = System.getProperty("user.home");

	/**
	 * This method store all attachments of the email This method get the specific
	 * email with the param
	 * 
	 * @param messagePositionParam Integer, This param is a number for to select the
	 *                             specific email on list
	 */
	public static void storeAttachmentsElements(int messagePositionParam) {

		Message objectMessage = messages[messagePositionParam];

		attachments = new ArrayList<File>();

		try {
			Multipart multipart = (Multipart) objectMessage.getContent();

			File fileAttachment;

			for (int i = 0; i < multipart.getCount(); i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				if (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())
						&& StringUtils.isBlank(bodyPart.getFileName())) {
					continue;
				}
				InputStream is = bodyPart.getInputStream();

				// Save attachment on Download directory
				fileAttachment = new File(homeRute + "\\Downloads\\" + bodyPart.getFileName());
				FileOutputStream fos = new FileOutputStream(fileAttachment);

				// Buffer of bytes
				byte[] buf = new byte[4096];
				int bytesRead;

				while ((bytesRead = is.read(buf)) != -1) {
					fos.write(buf, 0, bytesRead);
				}
				fos.close();
				attachments.add(fileAttachment);
			}
		} catch (Exception e) {
			System.out.println("Error getting attachments for Email");
		}
	}

	/**
	 * This method generate JPanel with the scroll of Emails
	 * 
	 * @param mailPanelBox JPanel, JPanel of view that it will have
	 * @param contenPane   JPanel, JPanel that containts all elements of view
	 * @return JPanel with all elements (Scroll emails and container that show the
	 *         context of specific email)
	 */
	public JPanel generateJComboBoxWithEmails(JPanel mailPanelBox, JPanel contenPane) {
		try {

			viewScroll = new JPanel();

			scrollEmails = new JComboBox();

			updateJComboBox();

			ScrollEmailListener.addScrollEmailListener(scrollEmails, mailPanelBox, contenPane);
			viewScroll.add(scrollEmails);
			return viewScroll;
		} catch (Exception e) {
			System.out.println("Error creating JScrollPane");
			return null;
		}
	}

	/**
	 * This method fill the JComboBox with the emails
	 */
	public static void updateJComboBox() {
		try {
			scrollEmails.addItem("EMAILS");
			for (int counter = 0; counter < messages.length; counter++) {
				Message objectMessage = messages[counter];

				String informationEmail = "Titulo:   " + objectMessage.getSubject() + "      "
						+ filterFromMessage(objectMessage.getFrom()[0].toString());
				System.out.println(informationEmail);
				scrollEmails.addItem(informationEmail);
			}
		} catch (Exception e) {
			System.out.println("error updating JScroll");
		}
	}

	/**
	 * This method generate JPanel with the content of specific email
	 * 
	 * @param position Integer, say the position of the email list
	 * @return JPanel with the content of specific email
	 */
	public static JPanel generateJEditorPaneEmail(int position) {
		try {
			Message objectMessage = messages[position];
			
			try {
				Multipart multipart = (Multipart) objectMessage.getContent();	
			} catch (Exception e) {
				String multipart = (String) objectMessage.getContent();
			}

			String bodyTextSave = "";
			bodyTextSave += "<p align= 'left'>Subject:   " + objectMessage.getSubject() + "</p>";
			bodyTextSave += "<p></p>";
			bodyTextSave += "<p align= 'left'> " + filterFromMessage(objectMessage.getFrom()[0].toString())
					+ "</p>";
			bodyTextSave += "<p></p>";
			bodyTextSave += getBodyText(objectMessage);
			String filterName = "";

			storeAttachmentsElements(position);

			for (int counterAttachments = 0; counterAttachments < attachments.size(); counterAttachments++) {
				filterName = searchOnlyNameAttachment(attachments.get(counterAttachments).toString());
				bodyTextSave.replace(attachments.get(counterAttachments).toString(), "");
				if(bodyTextSave.contains("<img")) {
					bodyTextSave = searchLinks(bodyTextSave);	
				}
				bodyTextSave += "<a href=file:///'" + attachments.get(counterAttachments).toString() + "'>" + filterName
						+ " </a>";
			}

			JPanel allEmailInfo = new JPanel();

			JEditorPane editor = new JEditorPane("text/html", bodyTextSave);

			editor.setEditable(false);
			Listeners.LinksEmailListener.addListenerLink(editor);

			JScrollPane scrollElement = new JScrollPane(editor);

			allEmailInfo.add(scrollElement);

			return allEmailInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error reading content about messages");
			return null;
		}
	}

	/**
	 * This method search and remove all links on the body of the email
	 * 
	 * @param bodyTextSave String, contain all body context of the email
	 * @return String, The filtered body text
	 */
	public static String searchLinks(String bodyTextSave) {
		char [] listOfCaracters = new char [bodyTextSave.length()];
		String labelImage = "";
		boolean dontSaveImageLink = false;
		for(int counter = 0; counter < bodyTextSave.length(); counter++) {
			if(dontSaveImageLink) {
				if(bodyTextSave.charAt(counter-1) == ">".charAt(0)) {
					dontSaveImageLink = false;
				}
			}
			if(bodyTextSave.charAt(counter) == "<".charAt(0)) {
				if(bodyTextSave.charAt(counter+1) == "i".charAt(0)) {
					if(bodyTextSave.charAt(counter+2) == "m".charAt(0)) {
						if(bodyTextSave.charAt(counter+3) == "g".charAt(0)) {
							dontSaveImageLink = true;	
						}else {
							if(!dontSaveImageLink) {
								listOfCaracters[counter] = bodyTextSave.charAt(counter);
							}	
						}
					}else {
						if(!dontSaveImageLink) {
							listOfCaracters[counter] = bodyTextSave.charAt(counter);
						}
					}
				}else {
					if(!dontSaveImageLink) {
						listOfCaracters[counter] = bodyTextSave.charAt(counter);
					}
				}
			}else {
				if(!dontSaveImageLink) {
					listOfCaracters[counter] = bodyTextSave.charAt(counter);
				}
			}
		}
		System.out.println(String.valueOf(listOfCaracters));
		return String.valueOf(listOfCaracters);
	}
	
	/*
	 * get the sender of Email
	 */
	public static String filterFromMessage(String fromParam) {
		String filterFrom = "";
		boolean checkCaracter = false;

		for (int counter = 0; counter < fromParam.length(); counter++) {
			char caracter = fromParam.charAt(counter);
			if (caracter == "<".charAt(0) || checkCaracter) {
				if (!checkCaracter) {
					filterFrom += "Remitente:     ";
					checkCaracter = true;
				} else {
					if (caracter == ">".charAt(0)) {
						break;
					} else {
						filterFrom += Character.toString(caracter);
					}
				}
			}
		}
		return filterFrom;
	}

	/**
	 * This method search the name of attachment
	 * 
	 * @param ruteAttachment, text that containt the rute of the attachment
	 * @return StringBuilder, the name of attachment in your pc
	 */
	public static String searchOnlyNameAttachment(String ruteAttachment) {
		try {
			char[] allCaracters = ruteAttachment.toCharArray();
			ArrayList<Character> allCaractersArray = new ArrayList<Character>();
			char elementSearch = "\\'".charAt(0);
			for (int counter = 0; counter < allCaracters.length; counter++) {
				if (allCaracters[allCaracters.length - 1 - counter] != elementSearch) {
					allCaractersArray.add(allCaracters[allCaracters.length - 1 - counter]);
				} else {
					break;
				}
			}
			for (int i = 0; i < allCaractersArray.size() / 2; i++) {
				char temp = allCaractersArray.get(i);
				allCaractersArray.set(i, allCaractersArray.get(allCaractersArray.size() - i - 1));
				allCaractersArray.set(allCaractersArray.size() - i - 1, temp);
			}

			StringBuilder builderName = new StringBuilder(allCaractersArray.size());
			for (Character ch : allCaractersArray) {
				builderName.append(ch);
			}

			return builderName.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method return the body part of the email
	 * 
	 * @param bodyPart Part, Part of the body
	 * @return String, the content of the email (Usually, text)
	 */
	public static String getBodyText(Part bodyPart) {
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
					if (bodyText != null) {
						return bodyText;
					}
				}
			}

			return null;
		} catch (Exception e) {
			System.out.println("Error getting body part about Message");
			return null;
		}
	}

	/**
	 * This method receive the all messages of the Folder emails and save those
	 * emails
	 */
	public void receiveAndSaveAllEmails() {
		try {
			messages = emailFolder.getMessages();
			System.out.println("you have " + messages.length + " messages");
		} catch (Exception e) {
			System.out.println("Error getting messages");
		}
	}

	/**
	 * This method establishes the folder of emails
	 */
	public static void setFolderEmails() {
		try {
			folderList = store.getDefaultFolder().list();

			emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);
		} catch (Exception e) {
			System.out.println("Error receive list of folders Email");
		}

	}

	/**
	 * Connect with gmail and save the store
	 * 
	 * @return Boolean
	 *         <ul>
	 *         <li>True, if this method can connect with the server
	 *         <li>False, if this method cannot connect with the server
	 *         </ul>
	 */
	public boolean connectMailServer() {
		try {
			store = session.getStore("imaps");
			store.connect(host, userName, passwd);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Establish all params for to connect with the Email server
	 * 
	 * @param hostParam          String, host of emails
	 * @param mailStoreTypeParam String, type of mails in this server
	 * @param userNameParam      String, User name param
	 * @param passwdParam        String, Password param
	 */
	public void setAllDataConnection(String hostParam, String mailStoreTypeParam, String userNameParam,
			String passwdParam) {
		host = hostParam;
		mailStoreType = mailStoreTypeParam;
		userName = userNameParam;
		passwd = passwdParam;
	}

	/**
	 * Establish the properties of connection
	 */
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

	// All get and set

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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Folder[] getFolderList() {
		return folderList;
	}

	public void setFolderList(Folder[] folderList) {
		this.folderList = folderList;
	}

	public Folder getEmailFolder() {
		return emailFolder;
	}

	public void setEmailFolder(Folder emailFolder) {
		this.emailFolder = emailFolder;
	}

	public static Message[] getMessages() {
		return messages;
	}

	public static void setMessages(Message[] messages) {
		MailMethods.messages = messages;
	}

	public static ArrayList<File> getAttachments() {
		return attachments;
	}

	public static void setAttachments(ArrayList<File> attachments) {
		MailMethods.attachments = attachments;
	}

	public static String getHomeRute() {
		return homeRute;
	}

	public static void setHomeRute(String homeRute) {
		MailMethods.homeRute = homeRute;
	}

	public static JComboBox getScrollEmails() {
		return scrollEmails;
	}

	public static void setScrollEmails(JComboBox scrollEmails) {
		MailMethods.scrollEmails = scrollEmails;
	}

	public static JPanel getViewScroll() {
		return viewScroll;
	}

	public static void setViewScroll(JPanel viewScroll) {
		MailMethods.viewScroll = viewScroll;
	}

}
