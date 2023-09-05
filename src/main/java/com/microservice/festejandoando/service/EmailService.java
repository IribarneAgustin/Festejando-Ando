package com.microservice.festejandoando.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.microservice.festejandoando.model.Booking;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailService;

    public void sendEmail() {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cliente@festejandoando.com");
        message.setTo("matitox98@gmail.com");
        message.setText("test");

        mailService.send(message);
    }

    public void notifyAdminAndClient(Booking booking) {

        SimpleMailMessage messageToClient = new SimpleMailMessage();
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

    private void prepareClientMessage(SimpleMailMessage message, String clientEmail, String name) {
        String emailToClient = "Gracias por comunicarte con FestejandoAndo {recipient}!, en las próximas horas te estaremos enviando un presupuesto adaptado para vos en base a lo que nos pediste.\n"
                +
                "Saludos,\nAgustina";
        emailToClient = emailToClient.replace("{recipient}", name != null ? name : "");
        message.setFrom("agusiri96@gmail.com"); // festejandoando
        message.setTo(clientEmail);
        message.setText(emailToClient);
        message.setSubject("Solicitud de reserva recibida");
    }

    private void prepareAdminMessage(SimpleMailMessage message, Booking booking) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(booking.getDate());

        String emailToAdmin = "Buenas noticias Agus!,\n" +
                "Recibiste una nueva solicitud de reserva para la fecha " + formattedDate + " desde la página web!\n" +
                "Puedes revisar toda la información desde el panel de administración, así como también confirmarla o rechazarla.\n\n"
                +
                "Datos del solicitante:\n" +
                "Nombre: " + (booking.getClient().getName() != null ? booking.getClient().getName() : "") + "\n" +
                "Email: " + booking.getClient().getEmail() + "\n" +
                "Teléfono: " + booking.getClient().getPhoneNumber();

        message.setFrom("agusiri96@gmail.com"); // festejandoando web site
        message.setTo("agusiri96@gmail.com"); // festejando
        message.setText(emailToAdmin);
        message.setSubject("Solicitud de reserva recibida");
    }

}
