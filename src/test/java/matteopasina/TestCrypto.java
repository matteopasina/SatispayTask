package matteopasina;

import matteopasina.authentication.Crypto;
import matteopasina.authentication.ICrypto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCrypto {
    private static ICrypto crypto;

    @Before
    public void setUp() {
        crypto = new Crypto();
    }

    @Test
    public void testGetDigest() {

        String result = crypto.getDigest("Test Digest");

        assertEquals( "SHA-256=nC9slvkp6QA5vYi7JmqmrKbLR9Fe2ntB2MYZKs6mNOc=", result);
    }

    @Test
    public void testGetSignature() {
        String result = crypto.getSignatureRSA_SHA256("Test Signature");
        String expectedSignature = "bavpE6o7P9ELX6AjgH9wwE5F6ALSSCErDnyX264VSruSEbtJ4jHt8At1KsfMzFdD6vRxY1JHeFrF8twWyCv6QmGctdjb67BeyznnsWHNY6Vcn/bumvXcleEBwBFbz36lfih2tF/Qb7XK0J7P7XadB+M1Ydub7pqoPY9VTSHzwg0BR0kALlNbfPY9ynXYp22IZlkOQWdmV77b1w3eHau0KQrGjpiuW8hx5VKyuztmlqwLrkPSOmkSCBU20+0D3zQJpzJO+bwGwrj9Pyaq3jCjtc2bkasUZ94WJIxIuZ6+HvcWe8ITw4AwafSE4gv2EjUbO6yADlSNoWh+ERwhy1gzOA==";

        assertEquals(expectedSignature, result);
    }
}
