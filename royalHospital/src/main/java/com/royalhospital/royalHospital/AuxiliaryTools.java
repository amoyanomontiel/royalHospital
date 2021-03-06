/**
 * 
 */
package com.royalhospital.royalHospital;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import conections.DBConection;

/**
 * Auxiliar Tools and utilities class
 * @author Cristina Montilla
 *
 */
public class AuxiliaryTools {

	public AuxiliaryTools() {

	}

	public static boolean checkDirectory(FTPClient ftpClient) {
		boolean isDirectory = false;
		FTPFile file = null;
		try {
			file = ftpClient.mlistFile(DataModel.actualUserPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (file.isDirectory()) {
			isDirectory = true;
		}
		return isDirectory;
	}

	public static boolean checkFile(FTPClient ftpClient) {
		boolean isFile = false;
		FTPFile file = null;
		try {
			file = ftpClient.mlistFile(DataModel.actualUserPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (file.isFile()) {
			isFile = true;
		}
		return isFile;
	}

	public static String actualDate() {
		Calendar date = Calendar.getInstance();
		String sysdate = "" + date.get(Calendar.DATE) + "/" + date.get(Calendar.DAY_OF_MONTH) + "/"
				+ date.get(Calendar.YEAR);
		return sysdate;
	}

	public static String actualTime() {
		Calendar date = Calendar.getInstance();
		String time = "" + date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE) + ":"
				+ date.get(Calendar.SECOND);
		return time;
	}

	public static void saveOperationAtDBRecord(int codUser, String operation, String file, String date,
			String time) {
		DBConection conectToDB = new DBConection();
		Statement state = null;
		try {
			state = conectToDB.getConect().createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			state.executeUpdate("Insert into historialftp values(" + codUser + ", '" + operation + "', '" + file
					+ "' , '" + date + "', '" + time + "')");
		} catch (SQLException e) {
			// Error
			e.printStackTrace();
		}
		try {
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
