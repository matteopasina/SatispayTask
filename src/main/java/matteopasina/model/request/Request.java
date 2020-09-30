package matteopasina.model.request;

import com.google.gson.Gson;
import matteopasina.model.request.body.RequestBody;
import matteopasina.utils.HTTPVerb;

import java.util.HashMap;

public class Request {

    private HTTPVerb httpVerb;
    private HashMap<String, String> headers;
    private RequestBody body;

    public Request(HTTPVerb httpVerb) {
        this.httpVerb = httpVerb;
        this.headers = new HashMap<>();
        this.body = null;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public void putHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public String getHeader(String key) {
        return this.headers.get(key);
    }

    public Object getBody() {
        return body;
    }

    public void setBody(RequestBody body) {
        this.body = body;
    }

    public HTTPVerb getHttpVerb() {
        return httpVerb;
    }

    public void setHttpVerb(HTTPVerb httpVerb) {
        this.httpVerb = httpVerb;
    }

    public String getJsonBody() {
        if(body == null) return null;
        else {
            Gson mapper = new Gson();
            return mapper.toJson(body);
        }
    }

    public String getStringHeaders() {
        StringBuilder headerString = new StringBuilder();

        for (HashMap.Entry<String, String> entry : this.headers.entrySet()) {
            headerString.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }

        return headerString.toString();
    }
}
