package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.net.ftp.FTPClient;

import conections.DBConection;
import conections.FTPConection;
import views.ErrorRoyalView;
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

		if (!userText.equals("") && !passwordText.equals("")) {
			
			if (!checkCredentials(userText, passwordText)) {
				ErrorRoyalView error = new ErrorRoyalView("El usuario y la contraseña no coinciden.", 1);
				error.setVisible(true);
				error.setLocationRelativeTo(null);
				loginView.getTxtPassword().setText("");
				loginView.getTxtUserName().setText("");
			} else {
				FTPConection ftpConect = new FTPConection(userText, passwordText);
				FTPClient ftpClient = ftpConect.createFTPClient();
				if (ftpClient.isConnected()) {
					MainRoyalView mainRoyal = new MainRoyalView(ftpClient);
					mainRoyal.setLocationRelativeTo(null);
					mainRoyal.setVisible(true);
				}
			} 
			
		} else {
			ErrorRoyalView error = new ErrorRoyalView("El campo usuario y/o contraseña no pueden estar vacios", 1);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
	}
	private boolean checkCredentials(String userText, String passwordText) {
		DBConection conectToDB = new DBConection();
		Statement state = null;
		boolean isCorrect = false;
		
		try {
			state = conectToDB.getConect().createStatement();
		} catch (SQLException | NullPointerException e) {
			ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar a la Base de Datos", 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
		try {
			ResultSet result = state.executeQuery("Select nameUser, password from usuarios where nameUser like '"
					+ userText.toString() + "' and password like '" + passwordText.toString() + "'");
			result.next();
			isCorrect = true;
		} catch (SQLException | NullPointerException e) {
			isCorrect = false;
		}
			try {
				state = conectToDB.getConect().createStatement();
			} catch (SQLException | NullPointerException e) {
				ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar a la Base de Datos", 0);
				error.setVisible(true);
				error.setLocationRelativeTo(null);
			}
			try {
				ResultSet result = state.executeQuery("Select nameUser, password from usuarios where nameUser like '"
						+ userText.toString() + "' and password like '" + passwordText.toString() + "'");
				result.next();
				isCorrect = true;
			} catch (SQLException | NullPointerException e) {
				isCorrect = false;
			}	
		return isCorrect;
	}
}