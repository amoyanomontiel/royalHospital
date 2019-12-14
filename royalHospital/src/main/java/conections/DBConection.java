package conections;

import java.sql.*;

import views.ErrorRoyalView;

public class DBConection {
	Connection conect;

	public DBConection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar a la Base de Datos");
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
		try {
			conect = DriverManager.getConnection("jdbc:mysql://localhost/royalhospital", "root", "");
		} catch (Exception e) {
			ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar a la Base de Datos");
			error.setVisible(true);
			error.setLocationRelativeTo(null);
		}
	}

	public Connection getConect() {
		return conect;
	}

	public void setConect(Connection conect) {
		this.conect = conect;
	}
	
	
}
