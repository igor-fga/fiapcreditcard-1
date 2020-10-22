package br.com.fiap.fiapcreditcard.service;

import br.com.fiap.fiapcreditcard.dto.ExtratoDTO;
import org.apache.logging.log4j.util.StringBuilders;
import org.springframework.stereotype.Service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class ExtratoServiceImpl implements ExtratoService {

    @Override
    public void SendExtrato(ExtratoDTO extratoDTO) {

        final String username = "ddronefiap@gmail.com";
        final String password = "12345@21";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ddronefiap@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(extratoDTO.getEmail()));
            message.setSubject("Extrato Consolidado");
            StringBuilder mailText = new StringBuilder();
            mailText.append("<table style=\"border: solid 1px black; border-collapse: collapse;\"><tr>");
            mailText.append("<th style=\"border: solid 1px black; padding:5px;\">Beneficiario</th>")
                    .append("<th style=\"border: solid 1px black; padding:5px;\">Produto</th>")
                    .append("<th style=\"border: solid 1px black; padding:5px;\">Valor</th>");
            mailText.append("</tr>");

            extratoDTO.getTransacoes().forEach(transacao -> mailText.append("<tr><td style=\"border: solid 1px black; padding:5px;\">")
                    .append(transacao.getBeneficiario()).append("</td><td style=\"border: solid 1px black; padding:5px;\">")
                    .append(transacao.getProduto()).append("</td><td style=\"border: solid 1px black; padding:5px;\">")
                    .append(transacao.getValor()).append("</td></tr>"));
            mailText.append("</table>");
            message.setContent(mailText.toString(), "text/html");

            extratoDTO.getTransacoes().get(0).getAluno();

            Transport.send(message);

            System.out.println("E-mail send!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
