package com.royalhospital.royalHospital;

import listeners.LoginListener;
import views.RoyalLoginView;

/**
 * Paquete controlador de la App
  *
 */
public class App 
{
    public static void main( String[] args )
    {        
    	RoyalLoginView initLogin = new RoyalLoginView("Usuario", "/views/royalhospital.png");
    	initLogin.setLocationRelativeTo(null);
    	initLogin.setVisible(true);
    	
    	// Test about mail
//        MailMethods objetoMail = new MailMethods();
//        objetoMail.setAllDataConnection("pop.gmail.com", "pop3", "jfernandezfernandez.sanjose@alumnado.fundacionloyola.net", "14674858");
//        objetoMail.setProperties();
//        objetoMail.connectMailServer();
//        objetoMail.setFolderEmails();
//        objetoMail.receiveAndSaveAllEmails();
//        objetoMail.generateJScrollPaneWithEmails();
        //objetoMail.storeAllMessages();
    }
}
