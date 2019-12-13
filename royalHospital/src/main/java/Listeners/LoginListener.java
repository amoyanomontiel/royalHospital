package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			System.out.println("Bien");
			MainRoyalView mainRoyal = new MainRoyalView();
			mainRoyal.setLocationRelativeTo(null);
			mainRoyal.setVisible(true);
		}
	}

	private boolean checkCredentials() {
		DBConection conectToDB = new DBConection();
		Statement state = null;
		boolean isCorrect = false;
		if (conectToDB != null) {
			if (!loginView.getTxtUserName().getText().equals("") && !loginView.getTxtPassword().getText().equals("")) {
				try {
					state = conectToDB.getConect().createStatement();
					System.out.println("state creado");
				} catch (SQLException e) {
					System.out.println("No va el state");
					// Dani pon ventana de error de "No se ha podido conectar a la Base de Datos,
					// compruebe la conexión ...."
					// Y vuelve a mostrarse la ventana de login
				}
				try {
					ResultSet result = state
							.executeQuery("Select nameUser, password from usuarios where nameUser like '"
									+ loginView.getTxtUserName().getText().toString() + "' and password like '"
									+ loginView.getTxtPassword().getText().toString() + "'");
					isCorrect = true;
				} catch (SQLException e) {
					System.out.println("inCorrect");
					System.out.println(e.getMessage());
					isCorrect = false;
				}

			}else {
				//Dani error el campo usuario y/o contraseña no puede estar vacio
				System.out.println("error vacio");
			}
		}else {
			//Dani error de no existe conexion con la BD
		}
		return isCorrect;
	}
}