package conections;

import java.sql.*;

import views.ErrorRoyalView;

public class DBConection {
	Connection conect;

	public DBConection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conect = DriverManager.getConnection("jdbc:mysql://localhost/royalhospital", "root", "");
			//conect = DriverManager.getConnection("jdbc:mysql://fdb25.awardspace.net:3306/3260240_royalhospital", "3260240_royalhospital", "12345678Abc");
		} catch (ClassNotFoundException | SQLException e1) {
			ErrorRoyalView error = new ErrorRoyalView("No se ha podido conectar a la Base de Datos", 0);
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
