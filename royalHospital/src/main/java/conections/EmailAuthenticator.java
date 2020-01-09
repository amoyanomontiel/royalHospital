package conections;

import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

public class EmailAuthenticator {

	private String user;
	private String password;

	public EmailAuthenticator(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public int AuthenticateEmail() {
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
			props.put("mail.smtp.user", user); // Definir al programa qu� cuenta de correo va a mandar el mail
			props.put("mail.smtp.clave", password); // La clave de la cuenta
			props.put("mail.smtp.auth", "true"); // Usar autenticaci�n mediante usuario y clave
			props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
			props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Para que no pete al usar el gmail

			Session session = Session.getDefaultInstance(props);

			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", user, password);
			return 0;
		} catch (AuthenticationFailedException e) {
			return 1;
		} catch (MessagingException e) {
			return 2;
		}
	}
}
