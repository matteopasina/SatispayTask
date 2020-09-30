package matteopasina.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;

public class KeyReader {
    public static String getPrivateKeyFromPEMFile() throws IOException {
        String strKeyPEM = "";
        ClassLoader classLoader = KeyReader.class.getClassLoader();
        File file = new File(classLoader.getResource(Constants.PRIVATE_KEY_FILE).getFile());
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(streamReader);
        String line;
        while ((line = br.readLine()) != null) {
            strKeyPEM += line + "\n";
        }
        br.close();
        strKeyPEM = strKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "")
                             .replace("-----END PRIVATE KEY-----", "")
                             .replace("\n", "");

        return strKeyPEM;
    }
}
