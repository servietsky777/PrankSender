/**
 * Project : Prank Sender
 * File    : PrankSender.java
 * Author  : Antoine Drabble & Guillaume Serneels
 * Date    : 16.04.2016
 */

import config.ConfigurationManager;
import model.mail.Message;
import prank.PrankGenerator;
import smtp.SmtpClient;

import java.io.IOException;
import java.util.List;

/**
 * A VERIFIER
 * Messages afficher par le client SMTP ??
 * Message de fin du programme dans le main ??
 *
 * Relire le rapport ! Mettre à jour l'UML, compléter la discussion avec le server
 * Développer un peu l'explication des classes
 * Est-ce que ça va avec le write ? Modifier mettre avec print ??
 * Ajouter logger ???
 * Mettre les images de la discussion
 */ 

/**
 * Prank email sending application.
 * Loads the 3 configurations files from the config folder :
 * - config.properties
 * - messages.utf8
 * - victims.utf8
 */
public class PrankSender {

    /**
     * main method for the prank email sending application
     *
     * @param args
     */
    public static void main(String[] args) {
        // Load configuation
        ConfigurationManager config;
        try {
            config = new ConfigurationManager();
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
            return;
        }

        // Generate pranks mails
        PrankGenerator pg = new PrankGenerator(config);
        List<Message> messages = pg.generateMessages();

        // Send prank mails with SMTP
        SmtpClient sc = new SmtpClient(config.getSmtpServerAddress(), config.getSmtpServerPort());
        try {
            sc.sendMessages(messages);
        } catch (IOException e) {
            System.out.println("Message not sent, IOException : " + e.getMessage());
        }
    }
}
