package com.royalhospital.royalHospital;

import views.RoyalLoginView;

/**
 * Principal class which launches the application
 * 
 * @author Cristina Montilla
 *
 */
public class App {
	/**
	 * Launches the application and starts with login view
	 * 
	 * @param args Application startup arguments
	 */
	public static void main(String[] args) {
		DataModel data = new DataModel();
		RoyalLoginView initLogin = new RoyalLoginView(data.getUserTag(), data.getBigRoyalLogoRoute());
		initLogin.setLocationRelativeTo(null);
		initLogin.setVisible(true);
	}
}
