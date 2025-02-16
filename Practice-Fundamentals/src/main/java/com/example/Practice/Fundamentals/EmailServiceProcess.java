package com.example.Practice.Fundamentals;


import com.example.Practice.Fundamentals.processes.emailSender.model.EmailDetails;
import com.example.Practice.Fundamentals.processes.emailSender.service.EmailService;
import com.example.Practice.Fundamentals.processes.emailSender.serviceInterface.EmailServiceInterface;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication

public class EmailServiceProcess {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EmailServiceProcess.class)
                .web(WebApplicationType.NONE)
                .run(args);
//        System.out.println("Beans in application context:");
//        for(String beanName : context.getBeanDefinitionNames()) {
//            System.out.println(beanName);
//        }

EmailServiceInterface emailServiceProcess = context.getBean(EmailServiceInterface.class);


        EmailDetails details = new EmailDetails("Hrithik","hrithik@salesgear.io",
                "Test Email Subject for separate process app",
                "This is a test email sent using the Spring Boot application. for testing independent processes.");
        emailServiceProcess.sendSimpleMail(details);
        emailServiceProcess.sendSimpleMail2(details);
        emailServiceProcess.sendSimpleMail(details);
        emailServiceProcess.sendSimpleMail2(details);
        emailServiceProcess.sendSimpleMail(details);
        emailServiceProcess.sendSimpleMail2(details);
    }
}
