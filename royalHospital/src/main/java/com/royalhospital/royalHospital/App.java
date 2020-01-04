package com.royalhospital.royalHospital;

import views.RoyalLoginView;

/**
 * Principal class which launches the application
 * 
 * @author Cristina Montilla / Daniel Cuenca
 *
 */
public class App {
	/**
	 * Launches the application and starts with login view
	 * 
	 * @param args Application startup arguments
	 */
	public static void main(String[] args) {
		RoyalLoginView initLogin = new RoyalLoginView("Usuario", "/views/royalhospital.png");
		initLogin.setLocationRelativeTo(null);
		initLogin.setVisible(true);
	}
}
