package com.royalhospital.royalHospital;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendNewMail2 {

	public static void main(String[] args) {
		SendNewMail2 mail = new SendNewMail2();
	}

	public SendNewMail2() {
		String destinatario = "thenapo212@gmail.com"; // A quien le quieres escribir.
		String asunto = "Correo de prueba enviado desde Java"; // El Asunto del correo

		enviarConGMail(destinatario, asunto);
	}

	private static void enviarConGMail(String destinatario, String asunto) {
		String remitente = "thenapo212@gmail.com"; // Qui�n manda el correo
		String clave = "N"; // Contrase�a del correo (cambiar por tu contrase�a)

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente); // Definir al programa qu� cuenta de correo va a mandar el mail
		props.put("mail.smtp.clave", clave); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticaci�n mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Para establecer el protocolo de seguridad de gmail

		Session session = Session.getDefaultInstance(props); // Inicia una sesión de conexión con el servidor de gmail
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));  // Definir a gmail qu� cuenta de correo va a mandar el mail
			message.addRecipients(Message.RecipientType.TO, destinatario); // Definir a gmail qui�n va a recibir el mail

			message.setSubject(asunto); // Definir cu�l va a ser el asunto del correo

			BodyPart messageBodyPart = new MimeBodyPart(); // Crear el cuerpo del mensaje

			messageBodyPart.setText("This is message body"); // Definir cu�l va a ser el mensaje que tendr� el mail en su cuerpo

			Multipart multipart = new MimeMultipart(); // Creamos el objeto multipart para poder a�adir archivos y texto al cuerpo del mail

			multipart.addBodyPart(messageBodyPart); // A�ado el objeto multipart al cuerpo del mensaje

			String[] filename = {"C:\\Users\\thena\\Desktop\\APLICACIONES DE SERVICIOS EN RED-20191211\\IMG_20170120_224344.jpg",
					"C:\\Users\\thena\\Desktop\\Esto Es Serio\\FP\\FP.docx", "C:\\Users\\thena\\Downloads\\aPRM1An_460swp.webp"}; // Defino cu�l van a ser los archivos del mensaje
			
			String[] nombres = {"IMG_20170120_224344.jpg", "FP.docx", "aPRM1An_460swp.webp"};
			if (filename.length != 0) {
				for (int i = 0; i < filename.length; i++) {
					messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(filename[i]); // A�adimos al objeto DataSource el archivo que hemos elegido
					messageBodyPart.setDataHandler(new DataHandler(source)); // A�adimos un manipulador de archivos al mensaje para que el pueda manejar correctamente el archivo dentro del mensaje
					messageBodyPart.setFileName(nombres[i]); // Establecemos el nombre con el que la persona que recibe el mail ver� el archivo adjunto
					multipart.addBodyPart(messageBodyPart); // A�ado el objeto multipart al cuerpo del mensaje
				}
			}
			
			message.setContent(multipart); // A�ade al mensaje todas las partes que se han creado
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, clave); // Realiza una conexi�n con gmail con tu cuenta de correo para mandar el mensaje
			transport.sendMessage(message, message.getAllRecipients()); // Manda el mensaje
			transport.close(); // Cierra la conexi�n con gmail
			
		} catch (MessagingException me) {
			System.out.println(me.getMessage());
			me.printStackTrace(); // Si se produce un error
		}
	}
}