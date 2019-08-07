package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.domain.Template;
import com.crud.tasks.exception.TemplateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildMail(String message, Template template) {
        Context context = prepareDefaultMail(message);

        if(template == Template.NEW_CARD) {
            context.setVariable("preview", "You have new task on your Trello Board");
            context.setVariable("tasks_url", "http://localhost:8080/");
        } else if(template == Template.SCHEDULED) {
            context.setVariable("preview", "Daily Trello Account update");
            context.setVariable("tasks_url", "https://asiagre.github.io");
        }
        return templateEngine.process("mail/trello-mail", context);
    }

    private Context prepareDefaultMail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("button", "Visit webside");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("company_config", companyConfig);

        return context;
    }

}
