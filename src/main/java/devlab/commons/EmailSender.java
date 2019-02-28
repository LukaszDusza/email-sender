package devlab.commons;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
}
