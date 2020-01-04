package listeners;

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

/**
 * Listener class for login button which check credentials and allows or denies
 * access to the application
 * 
 * @author Cristina Montilla / Daniel Cuenca
 *
 */
public class LoginListener implements ActionListener {

	RoyalLoginView loginView;

	/**
	 * Initializes the variable loginView
	 * 
	 * @param loginView The login View object
	 */
	public LoginListener(RoyalLoginView loginView) {
		this.loginView = loginView;
	}

	/**
	 * Button event function which allows or denies the access
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String userText = loginView.getTxtUserName().getText();
		String passwordText = loginView.getTxtPassword().getText();

		if (!userText.equals("") && !passwordText.equals("")) {
			if (!checkCredentials(userText, passwordText)) {
				ErrorRoyalView error = new ErrorRoyalView("El usuario o la contraseña no son correctos", 1);
				error.setVisible(true);
				error.setLocationRelativeTo(null);
				loginView.getTxtUserName().setText("");
				loginView.getTxtPassword().setText("");
			} else {
				loginView.getBtnLogin().setEnabled(false);
				FTPConection ftpConect = new FTPConection();
				FTPClient ftpClient = ftpConect.createFTPClient();
				String roll = getRoll(userText);
				if (ftpClient.isConnected() && roll != null) {
					MainRoyalView mainRoyal = new MainRoyalView(ftpClient, userText, roll);
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

	/**
	 * Function which search the user roll at data base
	 * 
	 * @param userText Username entered at login view
	 * @return String Roll of user
	 */
	private String getRoll(String userText) {
		DBConection con = new DBConection();
		Statement st = null;
		String roll = null;
		try {
			st = con.getConect().createStatement();
		} catch (SQLException e) {
			ErrorRoyalView error = new ErrorRoyalView("Error de conexión con la Base de Datos", 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}

		try {
			ResultSet rs = st.executeQuery("Select nameRoll from roll  where codRoll in ( Select codRoll from usuarios "
					+ "where nameUser like '" + userText.toString() + "');");
			rs.next();
			roll = rs.getString(1);
		} catch (SQLException e) {
			ErrorRoyalView error = new ErrorRoyalView("Error de conexión con la Base de Datos", 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
		try {
			st.close();
		} catch (SQLException e) {
			// Error
		}
		return roll;
	}

	/**
	 * Function which check credentials of user comparing with data base info
	 * 
	 * @param userText     Username entered at loin view
	 * @param passwordText Password entered at login view
	 * @return boolean Returns true if the credentials are correct or false if its
	 *         not
	 */
	private boolean checkCredentials(String userText, String passwordText) {
		DBConection conectToDB = new DBConection();
		Statement state = null;
		boolean isCorrect = false;

		try {
			state = conectToDB.getConect().createStatement();
		} catch (SQLException | NullPointerException e) {
			ErrorRoyalView error = new ErrorRoyalView("Error de conexión con la Base de Datos", 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
		try {
			ResultSet result = state.executeQuery("Select nameUser, password from usuarios where nameUser like '"
					+ userText.toString() + "' and password like '" + passwordText.toString() + "'");
			result.next();
			System.out.println(result.getString(1) + result.getString(2));
			isCorrect = true;
		} catch (SQLException | NullPointerException e) {
			isCorrect = false;
		}
		try {
			state.close();
		} catch (SQLException e) {
			// Error
		}
		return isCorrect;
	}
}