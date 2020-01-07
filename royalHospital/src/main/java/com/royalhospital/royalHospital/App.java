package com.royalhospital.royalHospital;

import views.MainMailView;

/**
 * Paquete controlador de la App
 *
 */
public class App {
	public static void main(String[] args) {
		// RoyalLoginView initLogin = new RoyalLoginView();
		// initLogin.setLocationRelativeTo(null);
		// initLogin.setVisible(true);

		// initLogin.getBtnLogin().addActionListener(new LoginListener(initLogin));
		// Test about mail
		MainMailView objectLoginMail = new MainMailView("Correo Electrónico", "mailIcon.png");
	}
}
