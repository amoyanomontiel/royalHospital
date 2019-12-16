package com.royalhospital.royalHospital;

import Listeners.LoginListener;
import views.RoyalLoginView;

/**
 * Paquete controlador de la App
  *
 */
public class App 
{
    public static void main( String[] args )
    {        
    	//RoyalLoginView initLogin = new RoyalLoginView();
    	//initLogin.setLocationRelativeTo(null);
    	//initLogin.setVisible(true);
    	
    	//initLogin.getBtnLogin().addActionListener(new LoginListener(initLogin));
        // Test about mail
        MailMethods objetoMail = new MailMethods();
        objetoMail.setAllDataConnection("pop.gmail.com", "pop3", "thenapo212@gmail.com", "N@pitoG@tito2");
        objetoMail.setProperties();
        objetoMail.connectMailServer();
        objetoMail.setFolderEmails();
        objetoMail.receiveAndSaveAllEmails();
        objetoMail.generateJComboBoxWithEmails();
    }
}
