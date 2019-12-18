package com.royalhospital.royalHospital;

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

	// Falta eliminar img del e-mail a la hora de abrirlo

	private Folder emailFolder;

	private static Message[] messages;

	private static ArrayList<File> attachments;

	private static String homeRute = System.getProperty("user.home");

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

	public JPanel generateJComboBoxWithEmails() {
		try {

			JPanel viewScroll = new JPanel();

			JComboBox scrollEmails = new JComboBox();

			scrollEmails.addItem("EMAILS");

			for (int counter = 0; counter < messages.length; counter++) {
				Message objectMessage = messages[counter];

				String informationEmail = "Titulo:   " + objectMessage.getSubject() + "      "
						+ filterFromMessage(objectMessage.getFrom()[0].toString());

				scrollEmails.addItem(informationEmail);
			}

			//ScrollEmailListener.addScrollEmailListener(scrollEmails);
			viewScroll.add(scrollEmails);
			return viewScroll;
		} catch (Exception e) {
			System.out.println("Error creating JScrollPane");
			return null;
		}
	}

	public static JPanel generateJEditorPaneEmail(int position) {
		try {
			Message objectMessage = messages[position];

			Multipart multipart = (Multipart) objectMessage.getContent();

			String bodyTextSave = "";
			bodyTextSave += "<p align= 'left'>Subject:   " + objectMessage.getSubject() + "</p>";
			bodyTextSave += "<p></p>";
			bodyTextSave += "<p align= 'left'>From:   " + filterFromMessage(objectMessage.getFrom()[0].toString()) + "</p>";
			bodyTextSave += "<p></p>";
			bodyTextSave += getBodyText(objectMessage);
			String filterName = "";

			storeAttachmentsElements(position);

			for (int counterAttachments = 0; counterAttachments < attachments.size(); counterAttachments++) {
				filterName = searchOnlyNameAttachment(attachments.get(counterAttachments).toString());
				bodyTextSave.replace(attachments.get(counterAttachments).toString(), "");
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
			System.out.println("Error reading content about messages");
			return null;
		}
	}

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

	public void receiveAndSaveAllEmails() {
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

			emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);
		} catch (Exception e) {
			System.out.println("Error receive list of folders Email");
		}

	}

	public boolean connectMailServer() {
		try {
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
}
