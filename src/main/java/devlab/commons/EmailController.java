package devlab.commons;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/email-send")
    public String send() {
        Context context = new Context();

        context.setVariable("greeting", "ten email został wysłany z mojej apikacji w Spring Boot :)");

        String body = templateEngine.process("email", context);
        emailSender.sendEmail("lukaszdusza280@gmail.com", "przykladowy mail", body);
        return "redirect:/";
    }
}
