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
	
	public SendNewMail(String sender, String password) {
		this.sender = sender;
		this.password = password;
	}
	
	public void sendNewGmail(String addressee, String subject, String body, ArrayList<UploadedFile> attached) {
		Properties props = establishGmailPropierties();
		Session session = Session.getInstance(props,
			    new javax.mail.Authenticator() {
			       protected PasswordAuthentication getPasswordAuthentication() {  
			       return new PasswordAuthentication(sender,password);  
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
			}
		} catch (AddressException e) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado dicha cuenta de correo", "Correo erróneo",
					JOptionPane.WARNING_MESSAGE);
		} catch (MessagingException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al mandar el mensaje, vuelve a intentarlo", "Error fatal",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/*
	 * Define the properties of the gmail server and define the gmail account that you are going to use
	 */
	public Properties establishGmailPropierties() {
		Properties props = new Properties();  
	    props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    return props;
	}
	
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
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al crear el mensaje, vuelve a intentarlo", "Error fatal",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}
		return multipart;
	}
}