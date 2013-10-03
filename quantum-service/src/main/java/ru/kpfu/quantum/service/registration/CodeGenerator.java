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
    private String salt;
    private Random random;

    public CodeGenerator(String salt) {
        this.random = new Random();
        this.salt = salt;
    }

    public String getCode() {
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
