package com.microservice.festejandoando.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.microservice.festejandoando.model.Booking;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailService;
    @Value("${spring.mail.username}")
    private String appEmail;

    public void notifyAdminAndClient(Booking booking) {

        MimeMessage messageToClient = mailService.createMimeMessage();
        SimpleMailMessage messageToAdmin = new SimpleMailMessage();
        prepareClientMessage(messageToClient, booking.getClient().getEmail(), booking.getClient().getName());
        prepareAdminMessage(messageToAdmin, booking);

        try {
            mailService.send(messageToClient);
            mailService.send(messageToAdmin);
        } catch (Exception e) {
            e.printStackTrace();
        };

    }

    private void prepareClientMessage(MimeMessage mimeMessage, String clientEmail, String name) {
        try {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        String imageDirectLink = "https://drive.google.com/uc?id=1_Y3aiFoizYB94c2M3IQsbGHL8-YYmVj1";

        String emailToClient = "<div style='text-align: center;'><img src='" + imageDirectLink + "'></div>"
                + "<br/><br/><div style='text-align: center;'>Gracias por comunicarte con FestejandoAndo {recipient}!, en las próximas horas te estaremos enviando un presupuesto adaptado para vos en base a lo que nos pediste.<br/><br/>"
                + "Saludos, Agustina</div>";
        
        
        helper.setText(emailToClient, true);
        
        emailToClient = emailToClient.replace("{recipient}", name != null ? name : "");
        helper.setFrom(appEmail);
        helper.setTo(clientEmail);
        helper.setSubject("Solicitud de reserva recibida");
        helper.setText(emailToClient, true);

        System.out.println("Sending mail from " + appEmail + " to " + clientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void prepareAdminMessage(SimpleMailMessage message, Booking booking) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(booking.getDate());

        String emailToAdmin = "Buenas noticias Agus!,\n" +
                "Recibiste una nueva solicitud de reserva para la fecha " + formattedDate + " desde la página web!\n" +
                "Acordate que podes revisar toda la información desde el panel de administración.\n\n"
                +
                "Datos de la reserva:\n" +
                "Nombre: " + (booking.getClient().getName() != null ? booking.getClient().getName() : "") + "\n" +
                "Email: " + booking.getClient().getEmail() + "\n" +
                "Teléfono: " + (booking.getClient().getPhoneNumber() != null ? booking.getClient().getPhoneNumber() : "") + "\n" +
                "Comentarios: " + (booking.getDescription() != null ? booking.getDescription() : "") + "\n" +
                "Artículos sugeridos agregados: "
                + (booking.getSuggestionNames() != null ? booking.getSuggestionNames() : "Ninguno");

        message.setFrom(appEmail); // festejandoAndo web site 
        message.setTo(appEmail);
        message.setText(emailToAdmin);
        message.setSubject("Solicitud de reserva recibida");
        System.out.println("Sending mail from " + appEmail + " to " + appEmail);
    }

}
