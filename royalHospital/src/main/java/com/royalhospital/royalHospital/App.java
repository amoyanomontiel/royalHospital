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
    	initLogin.setLocationRelativeTo(null);
    	initLogin.setVisible(true);
    	
    	initLogin.getBtnLogin().addActionListener(new LoginListener(initLogin));
//        // Test about mail
//        MailMethods objetoMail = new MailMethods();
//        objetoMail.setAllDataConnection("pop.gmail.com", "pop3", "jfernandezfernandez.sanjose@alumnado.fundacionloyola.net", "14674858");
//        objetoMail.setProperties();
//        objetoMail.connectMailServer();
//        objetoMail.setFolderEmails();
//        objetoMail.receiveAllEmails();
//        objetoMail.storeAllMessages();
        
        //MainRoyalView main = new MainRoyalView();
        //main.setVisible(true);
    }
}
