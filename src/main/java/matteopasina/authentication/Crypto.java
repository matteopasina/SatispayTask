package matteopasina.authentication;

import matteopasina.utils.Constants;
import matteopasina.utils.KeyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.KeyFactory;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Crypto implements ICrypto {

    private static final Logger logger = LoggerFactory.getLogger(Crypto.class);

    public String getSignature(String text) {
        try {
            Signature instance = Signature.getInstance(Constants.SHA_256_WITH_RSA);
            instance.initSign(getPrivateKey());
            instance.update((text).getBytes());
            return Base64.getEncoder().encodeToString(instance.sign());
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            logger.error("Error while computing signature with RSA-SHA256: "+e.toString());
            return null;
        }
    }

    public String getDigest(String text) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance(Constants.SHA_256);
            return Constants.SHA_256 + "=" + Base64.getEncoder().encodeToString(sha256.digest((text).getBytes()));
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error while computing sha-256 digest: "+e.toString());
            return null;
        }
    }

    private PrivateKey getPrivateKey() {
        String privateKey;
        try {
            privateKey = KeyReader.getPrivateKeyFromPEMFile();
        } catch (IOException e) {
            logger.error("Error while reading private key from file: "+e.toString());
            return null;
        }
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory kf = KeyFactory.getInstance(Constants.RSA);
            return kf.generatePrivate(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("Error while encoding rsa private key: "+e.toString());
            return null;
        }
    }
}

