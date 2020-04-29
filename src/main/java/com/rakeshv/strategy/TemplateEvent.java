package com.rakeshv.strategy;

import com.rakeshv.models.Template;
import com.rakeshv.repositories.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Component("TEMPLATE")
public class TemplateEvent implements EventType {
    @Autowired
    TemplateRepository templateRepository;

    @PostConstruct
    public void postConstruct() {
        if (templateRepository.findAll().size() == 0) {
            Template template = Template.builder().build();
            templateRepository.save(template);
        }
    }

    @Override
    public void processEvent(String[] action) {
        String function = action.length > 2 ? action[1].toLowerCase() + action[2].toLowerCase() :
                action[1].toLowerCase();
        try {
            this.getClass().getDeclaredMethod(function).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private Template getTemplate() {
        return templateRepository.findAll().get(0);
    }

    private void saveTemplate(Template template) {
        templateRepository.save(template);
    }

    public void create() {
        Template template = getTemplate();
        template.setTemplateCreate(template.getTemplateCreate() + 1);
        saveTemplate(template);
    }

    public void delete() {
        Template template = getTemplate();
        template.setTemplateDelete(template.getTemplateDelete() + 1);
        saveTemplate(template);
    }

    public void update() {
        Template template = getTemplate();
        template.setTemplateUpdate(template.getTemplateUpdate() + 1);
        saveTemplate(template);
    }

    public void downloadstart() {
        Template template = getTemplate();
        template.setTemplateDownloadStart(template.getTemplateDownloadStart() + 1);
        saveTemplate(template);
    }

    public void downloadsuccess() {
        Template template = getTemplate();
        template.setTemplateDownloadSuccess(template.getTemplateDownloadSuccess() + 1);
        saveTemplate(template);
    }

    public void downloadfailed() {
        Template template = getTemplate();
        template.setTemplateDownloadFailed(template.getTemplateDownloadFailed() + 1);
        saveTemplate(template);
    }

    public void copy() {
        Template template = getTemplate();
        template.setTemplateCopy(template.getTemplateCopy() + 1);
        saveTemplate(template);
    }

    public void extract() {
        Template template = getTemplate();
        template.setTemplateExtract(template.getTemplateExtract() + 1);
        saveTemplate(template);
    }

    public void upload() {
        Template template = getTemplate();
        template.setTemplateUpload(template.getTemplateUpload() + 1);
        saveTemplate(template);
    }

    public void cleanup() {
        Template template = getTemplate();
        template.setTemplateCleanup(template.getTemplateCleanup() + 1);
        saveTemplate(template);
    }
}
