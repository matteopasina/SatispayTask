package matteopasina.authentication;


public interface ICrypto {

    String getSignature(String text);

    String getDigest(String text);
}
