package matteopasina.authentication;


public interface ICrypto {

    String getSignatureRSA_SHA256(String string);

    String getDigest(String string);
}
