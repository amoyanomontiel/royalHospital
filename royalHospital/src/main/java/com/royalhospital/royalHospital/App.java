package com.royalhospital.royalHospital;

import Listeners.LoginListener;
import views.RoyalLoginView;

//import views.MainRoyalView;

/**
 * Paquete controlador de la App
  *
 */
public class App 
{
    public static void main( String[] args )
    {        
    	RoyalLoginView initLogin = new RoyalLoginView();
    	initLogin.setVisible(true);
    	
    	initLogin.getBtnLogin().addActionListener(new LoginListener());
        // Test about mail
        MailMethods objetoMail = new MailMethods();
        objetoMail.setAllDataConnection("pop.gmail.com", "pop3", "jfernandezfernandez.sanjose@alumnado.fundacionloyola.net", "14674858");
        objetoMail.setProperties();
        objetoMail.connectMailServer();
        objetoMail.setFolderEmails();
        objetoMail.receiveAndSaveAllEmails();
        objetoMail.generateJScrollPaneWithEmails();
        //objetoMail.storeAllMessages();
    }
}
