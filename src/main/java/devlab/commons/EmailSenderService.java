package devlab.commons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderService implements EmailSender{

    private JavaMailSender javaMailSender;

    @Value("${email.reply}")
    String replyTo;

    @Value("${email.from}")
    String from;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String content) {
        MimeMessage mail = javaMailSender.createMimeMessage();

        try {

            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo(replyTo);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(content, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mail);
    }
}
