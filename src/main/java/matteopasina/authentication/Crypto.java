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


    /**
     * This methods takes the privateKey from the pem file, creates the PrivateKey object
     * and signs the string in input with it. Then it returns the base64 encoded signature
     * @param string
     * @return base64 encoded signature
     */
    public String getSignatureRSA_SHA256(String string) {
        String privateKey;
        try {
            privateKey = KeyReader.getPrivateKeyFromPEMFile();
        } catch (IOException e) {
            logger.error("Error while reading private key from file: "+e.toString());
            return null;
        }
        PrivateKey pk = generatePrivateKey(privateKey);
        return computeSignature(string, pk);
    }

    /**
     * This method computes the SHA-256 digest of the input string and then returns it encoded base64
     * @param string
     * @return String in this format: SHA-256=<base64 encoded digest>
     */
    public String getDigest(String string) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance(Constants.SHA_256);
            return Constants.SHA_256 + "=" + Base64.getEncoder().encodeToString(sha256.digest((string).getBytes()));
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error while computing sha-256 digest: "+e.toString());
            return null;
        }
    }

    /**
     * This method computes the encoding of the input string with SHA-256 with RSA
     * and returns a base64 encoded string
     * @param string
     * @param privateKey
     * @return Base64 string of RSA with SHA-256 signature
     */
    private String computeSignature(String string, PrivateKey privateKey) {
        try {
            Signature instance = Signature.getInstance(Constants.SHA_256_WITH_RSA);
            instance.initSign(privateKey);
            instance.update((string).getBytes());
            return Base64.getEncoder().encodeToString(instance.sign());
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            logger.error("Error while computing signature with RSA-SHA256: "+e.toString());
            return null;
        }
    }

    /**
     * This method decodes from base64 the provided private key, then encodes the bytes with PKCS #8
     * (since it is a private key), it generates the RSA private key object from this and returns it
     * @param privateKey
     * @return RSA PrivateKey object
     */
    private PrivateKey generatePrivateKey(String privateKey) {
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

