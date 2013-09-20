package ru.kpfu.quantum.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.quantum.service.mailing.MailService;

/**
 * @author sala
 */
@Controller
public class TestController {
    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/test/send", method = RequestMethod.GET)
    public String renderTestSend() {
        return "test/send";
    }

    @RequestMapping(value = "/test/send", method = RequestMethod.POST)
    @ResponseBody
    public String processTestSend(@RequestParam String receiver) {
        mailService.sendHelloWorld(receiver);
        return "ok";
    }
}
