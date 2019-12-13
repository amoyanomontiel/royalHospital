package conections;

import java.sql.*;

public class DBConection {
	Connection conect;

	public DBConection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// Dani pon ventana de error de "No se ha podido conectar a la Base de Datos"
			// Y vuelve a mostrarse la ventana de login
		}
		try {
			Connection conection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/comunidad", "root",
					"");
			conect = DriverManager.getConnection("jdbc:mysql://localhost/royalhospital", "root", "");
		} catch (Exception e) {
			// Dani pon ventana de error de "No se ha podido conectar a la Base de Datos"
			// Y vuelve a mostrarse la ventana de login
		}
	}

	public Connection getConect() {
		return conect;
	}

	public void setConect(Connection conect) {
		this.conect = conect;
	}
	
	
}
