package ru.kpfu.quantum.service.mailing;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * @author sala
 */
@Service
public class MailManager {
    private Configuration configuration;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        this.configuration.setObjectWrapper(new DefaultObjectWrapper());
        this.configuration.setClassForTemplateLoading(MailManager.class, "/mail");
    }

    @PostConstruct
    public void init() {
        if(configuration == null) {
            final Configuration defaultConfiguration = new Configuration();
            defaultConfiguration.setDefaultEncoding("UTF-8");
            setConfiguration(defaultConfiguration);
        }
    }

    /**
     * Возвращает письмо по шаблону
     * @param path путь до шаблона
     * @return текст письма
     */
    public String getMail(String path) {
        return getMail(path, new HashMap<String, Object>());
    }

    /**
     * Возвращает письмо по шаблону и параметрам
     * @param path путь до шаблона
     * @param data параметры
     * @return текст письма
     */
    public String getMail(String path, Map<String, ?> data) {
        try {
            final Template template = configuration.getTemplate(path);
            StringWriter stringWriter = new StringWriter();
            template.process(data, stringWriter);
            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
