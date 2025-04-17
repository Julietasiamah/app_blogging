package it.epicode.app_blogging.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendConfermaAutore (String to, String nome, String s){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Registrazione completata");
        message.setText("Ciao " + nome + " la tua registrazione è avvenuta con successo");
        mailSender.send(message);
    }
}
