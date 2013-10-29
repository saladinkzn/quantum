import com.google.gson.Gson;
import ru.kpfu.quantum.service.integration.IntegrationService;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author sala
 */
public class TestIntegrationService {

    public static void main(String[] args) throws Exception {
        IntegrationService integrationService = new IntegrationService();
        integrationService.setHost("localhost");
        integrationService.setPort(59991);
        final String code = "a :: bool -> bool\n" +
                "a x =do\n" +
                "    asd <- hadamard b \n" +
                "    asd <- qnot a \"controlled\"  c\n" +
                "    asd <- hadamard a ";
//        final String code = "main :: (b a c ) -> (b a c )\n" +
//                "main (b a c ) = do \n" +
//                "    a <- hadamard a \n" +
//                "    a c <- qnot a c \n" +
//                "    a <- hadamard a";
        final String circuit = integrationService.codeToCircuit(code);
        System.out.println(circuit);
        final String code2  = integrationService.circuitToCode(circuit);
        System.out.println(code2);
        final String code3 = (String) (new Gson().fromJson(code2, List.class).get(0));
        System.out.println(code3);
        final String circuit2 = integrationService.codeToCircuit(code3);
        System.out.println(circuit2);
        //
        final byte[] bytes = integrationService.codeToFile(code);
        try(FileOutputStream fos = new FileOutputStream("temp.png", false); BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(bytes))) {
            int read;
            while ((read = bis.read()) != -1) {
                fos.write(read);
            }
            fos.flush();
        }
        //
        final String singleCircuit = circuit.substring(1, circuit.length() - 2);
        final byte[] bytes2 = integrationService.circuitToFile(singleCircuit);
        try(FileOutputStream fos = new FileOutputStream("temp2.png", false); BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(bytes2))) {
            int read;
            while ((read = bis.read()) != -1) {
                fos.write(read);
            }
            fos.flush();
        }
    }
}
