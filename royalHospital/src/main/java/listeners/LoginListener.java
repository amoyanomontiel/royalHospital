package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.net.ftp.FTPClient;

import com.royalhospital.royalHospital.DataModel;

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
	DataModel data = new DataModel();

	/**
	 * Initializes class variables
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
				ErrorRoyalView error = new ErrorRoyalView(data.getPasswordUserIncorrectMsg(), 1);
				error.setVisible(true);
				error.setLocationRelativeTo(null);
				loginView.getTxtUserName().setText("");
				loginView.getTxtPassword().setText("");
			} else {
				loginView.dispose();
				FTPConection ftpConect = new FTPConection();
				FTPClient ftpClient = ftpConect.createFTPClient();
				String rol = getRol(userText);
				if (ftpClient.isConnected() && rol != null) {
					MainRoyalView mainRoyal = new MainRoyalView(ftpClient, userText, rol);
					mainRoyal.setLocationRelativeTo(null);
					mainRoyal.setVisible(true);
				}
			}

		} else {
			ErrorRoyalView error = new ErrorRoyalView(data.getNoEmptyLoginFieldsMsg(), 1);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
	}

	/**
	 * Function which search the user rol at data base
	 * 
	 * @param userText Username entered at login view
	 * @return String Rol of user
	 */
	private String getRol(String userText) {
		DBConection con = new DBConection();
		Statement st = null;
		String rol = null;
		try {
			st = con.getConect().createStatement();
		} catch (SQLException e) {
			ErrorRoyalView error = new ErrorRoyalView(data.getDbConectionError(), 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}

		try {
			ResultSet rs = st.executeQuery("Select nameRoll from roll  where codRoll in ( Select codRoll from usuarios "
					+ "where nameUser like '" + userText.toString() + "');");
			rs.next();
			rol = rs.getString(1);
		} catch (SQLException e) {
			ErrorRoyalView error = new ErrorRoyalView(data.getDbConectionError(), 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
		try {
			st.close();
		} catch (SQLException e) {
			// DB conection still open (user doesn't care about that)
		}
		return rol;
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
			ErrorRoyalView error = new ErrorRoyalView(data.getDbConectionError(), 0);
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
		try {
			ResultSet result = state.executeQuery(
					"Select nameUser, password, codUser from usuarios where nameUser like '" + userText.toString()
							+ "' and password like '" + encryptPassword(passwordText.toString()) + "'");
			result.next();
			isCorrect = true;
			DataModel.codActualUser = result.getInt(3);
		} catch (SQLException | NullPointerException e) {
			isCorrect = false;
		}
		try {
			state.close();
		} catch (SQLException e) {
			// DB conection still open (user doesn't care about that)
		}
		return isCorrect;
	}

	/**
	 * Function for encrypt password
	 * 
	 * @param String password user password
	 * @return String encrypted password
	 */
	private String encryptPassword(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}