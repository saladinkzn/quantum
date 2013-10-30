package ru.kpfu.quantum.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.quantum.service.mailing.MailService;
import ru.kpfu.quantum.service.registration.CodeGenerator;
import ru.kpfu.quantum.spring.entities.Invite;
import ru.kpfu.quantum.spring.repository.InviteRepository;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sala
 */
@Controller
public class AdminController {
    @Autowired
    InviteRepository inviteRepository;

    @Autowired
    CodeGenerator codeGenerator;

    @Autowired
    MailService mailService;

    private boolean authenticate(String user) throws IOException {
        if(user == null) {
            return false;
        }
        if(!user.toUpperCase().startsWith("BASIC ")) {
            // supporting basic authorization only
            return false;
        }
        // Get encoded user and password, comes after "BASIC"
        final String userPassEncoded = user.substring("BASIC ".length());
        sun.misc.BASE64Decoder dec = new BASE64Decoder();
        String userpassDecoded = new String(dec.decodeBuffer(userPassEncoded));
        //
        return "admin:password".equals(userpassDecoded);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String renderAdmin(HttpServletResponse response, @RequestHeader(value = "Authorization", required = false) String authorization) throws IOException {
        if(!authenticate(authorization)) {
            response.setHeader("WWW-Authenticate", "BASIC realm=\"Welcome to quantum admin\"");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return "admin/admin";
    }

    @RequestMapping(value = "/admin/sendInvite", method = RequestMethod.POST)
    public String sendInvite(@RequestParam String email) {
        final String code = codeGenerator.getInviteCode();
        final Invite invite = new Invite(code);
        mailService.sendInvite(email, code);
        inviteRepository.save(invite);
        return "admin/invite/inviteSent";

    }

}
