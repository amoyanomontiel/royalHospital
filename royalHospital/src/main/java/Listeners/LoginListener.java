package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

import conections.DBConection;
import views.MainRoyalView;
import views.RoyalLoginView;

public class LoginListener implements ActionListener {

	RoyalLoginView loginView;

	public LoginListener(RoyalLoginView loginView) {
		this.loginView = loginView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (!checkCredentials()) {
			// Dani mensaje de error de "No ha introducido correctamente sus credenciales,
			// vuelva a intentarlo"
			// Y vuelve a mostrarse la ventana login con los textfield vacíos
			loginView.getTxtPassword().setText("");
			loginView.getTxtUserName().setText("");
		} else {
			MainRoyalView mainRoyal = new MainRoyalView();
		}
	}

	private boolean checkCredentials() {
		DBConection conectToDB = new DBConection();
		Statement state = null;
		boolean isCorrect = false;
		if (conectToDB != null) {
			try {
				state = conectToDB.getConect().createStatement();
			} catch (SQLException e) {
				// Dani pon ventana de error de "No se ha podido conectar a la Base de Datos,
				// compruebe la conexión ...."
				// Y vuelve a mostrarse la ventana de login
			}
			try {
				Resultset result = (Resultset) state
						.executeQuery("Select nameUser, password from royalhospital where nameUser like '"
								+ loginView.getTxtUserName().getText() + "' and password like '"
								+ loginView.getTxtPassword().getText() + "'");
				isCorrect = true;
			} catch (SQLException e) {
				isCorrect = false;
			}

		}
		return isCorrect;
	}
}