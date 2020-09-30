package matteopasina.utils;

public class Constants {
    public static final String REQUEST_TARGET = "(request-target)";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String DIGEST = "Digest";
    public static final String CONTENT_LENGTH = "Content-Length";

    public static final String RSA = "RSA";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA_256_WITH_RSA = "SHA256withRSA";

    public static final String SIGNATURE_STRING = "Signature keyId=\"signature-test-66289\", algorithm=\"rsa-sha256\", headers=\"%s\", signature=\"%s\"";

    public static final String ENDPOINT_ROOT = "https://staging.authservices.satispay.com";
    public static final String ENDPOINT = "/wally-services/protocol/tests/signature";

    public static final String PRIVATE_KEY_FILE = "client-rsa-private-key.pem";
}
