package com.royalhospital.royalHospital;

/**
 * Paquete controlador de la App
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
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
