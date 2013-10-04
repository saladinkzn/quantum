package ru.kpfu.quantum.service.registration;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author sala
 */
public class CodeGenerator {
    private String inviteSalt;
    private Random random;
    private String remindSalt;

    public CodeGenerator(String inviteSalt, String remindSalt) {
        this.random = new Random();
        this.inviteSalt = inviteSalt;

    }

    public String getInviteCode() {
        return getCode(inviteSalt);
    }

    public String getRemindCode() {
        return getCode(remindSalt);
    }

    private String getCode(String salt) {
        try {
            long randomVal = random.nextLong();
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            final String saltedString = String.valueOf(randomVal) + salt;
            final byte[] digest = md5.digest(saltedString.getBytes(Charset.forName("UTF-8")));
            return DatatypeConverter.printHexBinary(digest);
        } catch (NoSuchAlgorithmException ignored) {
            throw new RuntimeException("Huh? No MD5 algorithm was found");
        }
    }
}
