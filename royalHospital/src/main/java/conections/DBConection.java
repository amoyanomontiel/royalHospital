package conections;

import java.sql.*;

import com.royalhospital.royalHospital.DataModel;

import views.ErrorRoyalView;
/**
 * DB conection class. Creates the conection with data base.
 * @author Cristina Montilla
 *
 */
public class DBConection {
	Connection conect;
	DataModel data = new DataModel();
	
/**
 * Creates new conection checking credentials
 */
	public DBConection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conect = DriverManager.getConnection(data.getDb(), data.getDbUser(), data.getDbPassword());
			//conect = DriverManager.getConnection("jdbc:mysql://fdb25.awardspace.net:3306/3260240_royalhospital", "3260240_royalhospital", "12345678Abc");
		} catch (ClassNotFoundException | SQLException e1) {
			ErrorRoyalView error = new ErrorRoyalView(data.getDbConectionError(), 0);
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
