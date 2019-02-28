package devlab.commons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
public class EmailController {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    public EmailController(EmailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Value("${email.recipient}")
    String emailRecipient;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/send-email-from-input")
    public String sendEmailFromInput(@ModelAttribute MyEmail myEmail) {

        Context context = new Context();
        context.setVariable("body", myEmail.getBody());
        String template = templateEngine.process("template", context); //'email' is a file in resource directory -> .html
        emailSender.sendEmail(myEmail.getAddress(), myEmail.getSubject() , template);
        return "index";
    }

    @GetMapping("/send-email")
    public String sendEmail() {
        return "sender";
    }
}
