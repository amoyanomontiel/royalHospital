package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.net.ftp.FTPClient;

import conections.DBConection;
import conections.FTPConection;
import views.MainRoyalView;
import views.RoyalLoginView;

public class LoginListener implements ActionListener {

	RoyalLoginView loginView;

	public LoginListener(RoyalLoginView loginView) {
		this.loginView = loginView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String userText = loginView.getTxtUserName().getText();
		String passwordText = loginView.getTxtPassword().getText();
		if (!checkCredentials(userText, passwordText)) {
			// Dani mensaje de error de "No ha introducido correctamente sus credenciales,
			// vuelva a intentarlo"
			// Y vuelve a mostrarse la ventana login con los textfield vacíos
			loginView.getTxtPassword().setText("");
			loginView.getTxtUserName().setText("");
		} else {
			FTPConection ftpConect = new FTPConection(userText, passwordText);
			FTPClient ftpClient = ftpConect.createFTPClient();
				System.out.println("conectado a server ");
			MainRoyalView mainRoyal = new MainRoyalView(ftpClient);
			mainRoyal.setLocationRelativeTo(null);
			mainRoyal.setVisible(true);
		}
	}

	private boolean checkCredentials(String userText, String passwordText) {
		DBConection conectToDB = new DBConection();
		Statement state = null;
		boolean isCorrect = false;
		if (!userText.equals("") && !passwordText.equals("")) {
			try {
				state = conectToDB.getConect().createStatement();
			} catch (SQLException e) {
				// Dani pon ventana de error de "No se ha podido conectar a la Base de Datos,
				// compruebe la conexión ...."
				// Y vuelve a mostrarse la ventana de login
			}
			try {
				ResultSet result = state.executeQuery("Select nameUser, password from usuarios where nameUser like '"
						+ userText.toString() + "' and password like '" + passwordText.toString() + "'");
				isCorrect = true;
			} catch (SQLException e) {
				System.out.println("inCorrect");
				System.out.println(e.getMessage());
				isCorrect = false;
			}
		} else {
			// Dani error el campo usuario y/o contraseña no puede estar vacio
		}
		return isCorrect;
	}
}