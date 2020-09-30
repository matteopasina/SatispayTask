package matteopasina.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import matteopasina.authentication.ICrypto;
import matteopasina.model.request.Request;
import matteopasina.model.response.Response;
import matteopasina.model.response.body.ResponseBody;
import matteopasina.utils.Constants;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class AuthService implements IAuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private URL url;
    private ICrypto crypto;

    public AuthService(ICrypto crypto) throws MalformedURLException {
        this.crypto = crypto;
        this.url = new URL(Constants.ENDPOINT_ROOT + Constants.ENDPOINT);
        logger.info("Auth Service created with url: " + this.url);
    }

    public Response call(Request request) {

        // If we are able to sign this request we start the call
        if(signRequest(request)){

            logger.info("Headers generated: " + request.getStringHeaders());

            logger.info("Start " + request.getHttpVerb().name() + " call");
            HttpURLConnection conn = setUpConnection(request);

            // If we have a body (POST and PUT requests)
            String requestBody = request.getJsonBody();
            if(requestBody != null && requestBody.length() > 0) {
                logger.info("Request body: " + requestBody);
                convertRequestBodyToBytes(conn, requestBody);
            }

            String responseBody = readInputStream(conn);

            try {

                logger.info("Response status: " + conn.getResponseCode());
                logger.info("Response message: " + conn.getResponseMessage());
                logger.info("Response body: " + responseBody);

                Response response = new Response(ResponseBody.jsonToObj(responseBody),
                        conn.getResponseMessage(),
                        conn.getResponseCode());

                conn.disconnect();

                return response;

            } catch (IOException e) {
                logger.error("Error while consuming connection: " + e.toString());
                return null;
            }
        } else {
            logger.error("Failed to sign request: " + request.getHttpVerb() + " " +
                    request.getStringHeaders() + " " + request.getJsonBody());
            return null;
        }
    }

    private HttpURLConnection setUpConnection(Request request) {
        try {

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(request.getHttpVerb().name());

            for (HashMap.Entry<String, String> entry : request.getHeaders().entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }

            return conn;
        } catch (IOException e) {
            logger.error("Unable to open connection: " + e.toString());
            return null;
        }
    }

    private void convertRequestBodyToBytes(HttpURLConnection conn, String body) {
        try {

            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(body.getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            logger.error("Error converting request body to bytes: " + e.toString());
        }
    }

    private String readInputStream(HttpURLConnection conn) {
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();

        } catch (IOException e) {
            logger.error("Error while reading inputStream from HttpURLConnection: " + e.toString());
            return null;
        }
    }

    private Boolean signRequest(Request request) {

        HashMap<String, String> httpHeaders = request.getHeaders();
        String httpVerb = request.getHttpVerb().name();
        String requestBody = request.getJsonBody();

        StringBuilder headersFields = new StringBuilder(Constants.REQUEST_TARGET);
        StringBuilder signature = new StringBuilder(Constants.REQUEST_TARGET + ": " + httpVerb.toLowerCase() + " " + Constants.ENDPOINT);

        if(requestBody != null && requestBody.length() > 0) {
            addBodyHeaders(requestBody, httpHeaders);
        }

        for (HashMap.Entry<String, String> entry : httpHeaders.entrySet()) {
            headersFields.append(" " + entry.getKey().toLowerCase());
            signature.append("\n" + entry.getKey().toLowerCase() + ": " + entry.getValue());
        }

        String authSignature = this.crypto.getSignature(signature.toString());
        if (authSignature != null) {
            String authHeader = String.format(Constants.SIGNATURE_STRING, headersFields, authSignature);
            httpHeaders.put("Authorization", authHeader);
            return true;
        } else {
            logger.error("Signature computed for the message is null");
            return false;
        }
    }

    private void addBodyHeaders(String requestBody, HashMap<String, String> httpHeaders) {
        byte[] bodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);
        String digest = this.crypto.getDigest(requestBody);
        if (digest != null) {
            httpHeaders.put(Constants.CONTENT_TYPE, "application/json");
            httpHeaders.put(Constants.CONTENT_LENGTH, Integer.toString(bodyBytes.length));
            httpHeaders.put(Constants.DIGEST, this.crypto.getDigest(requestBody));
        } else {
            logger.error("Not able to compute digest of body");
        }
    }
}
