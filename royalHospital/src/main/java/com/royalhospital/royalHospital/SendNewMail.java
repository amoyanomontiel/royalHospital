/**
 * This class contains the file name and the file path of every file attached to the message
 * @author Fernando Cañadas Ortega
 * @version 1.0
 * Realizado el 9 november 2020
 */

package com.royalhospital.royalHospital;

import java.util.ArrayList;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class SendNewMail {

	private String sender;
	private String password;
	private DataModel model = new DataModel(); 

	/**
	 * Builder of this class that fill the email sender and email password of the
	 * email account
	 */
	public SendNewMail(String sender, String password) {
		this.sender = sender;
		this.password = password;
	}


	/**
	 * Call the method that defines the properties of the mail server, log in with
	 * your email account, establish who will be the addressees, the subject, the
	 * body and the attachments of the mail and when everything is already, send the
	 * message
	 * 
	 * @param addressee string that contains the addressee to whom the message is
	 *                  aimed to
	 * @param subject   string that contains the subject of the message
	 * @param body      string that contains the message body
	 * @param attached  ArrayList of the class UploadedFile that contains the file
	 *                  path and the file name of every file that will be attached
	 *                  to the message
	 * @return boolean that establish if the message is sended correctly or not
	 */
	public boolean sendNewGmail(String addressee, String subject, String body, ArrayList<UploadedFile> attached) {
		Properties props = establishGmailPropierties();
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.addRecipients(Message.RecipientType.TO, addressee);
			message.setSubject(subject);
			Multipart multipart = createMessage(body, attached);

			if (multipart != null) {
				message.setContent(multipart);
				Transport transport = session.getTransport("smtp");
				transport.connect("smtp.gmail.com", sender, password);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
				return true;
			} else {
				return false;
			}
		} catch (AddressException e) {
			JOptionPane.showMessageDialog(null, model.getNoAccountFound(), model.getEmailHeader(),
					JOptionPane.WARNING_MESSAGE);
			return false;
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, model.getGeneralError(),
					model.getEmailHeader(), JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}

	/**
	 * Define the properties of the mail server and define the gmail account that
	 * you are going to use
	 * 
	 * @return Propierties of the server and the account that will be used
	 */
	public Properties establishGmailPropierties() {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		return props;
	}

	/**
	 * Create the message body and add the attachments to the message so they can be
	 * sent
	 * 
	 * @param body     string that contains the message body
	 * @param attached ArrayList of the class UploadedFile that contains the file
	 *                 path and the file name of every file that will be attached to
	 *                 the message
	 * @return Multipart that contains the body message and the attached files ready to be added to the message
	 */
	public Multipart createMessage(String body, ArrayList<UploadedFile> attached) {
		Multipart multipart = new MimeMultipart();
		try {
			BodyPart messageBodyPart = new MimeBodyPart(); // Crear el cuerpo del mensaje
			messageBodyPart.setText(body);
			multipart.addBodyPart(messageBodyPart);

			if (attached.size() != 0) {
				for (UploadedFile currentFile : attached) {
					messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(currentFile.getFilePath());
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(currentFile.getFileName());
					multipart.addBodyPart(messageBodyPart);
				}
			}
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, model.getCreatingMessageError(),
					model.getEmailHeader(), JOptionPane.WARNING_MESSAGE);
			return null;
		}
		return multipart;
	}
}