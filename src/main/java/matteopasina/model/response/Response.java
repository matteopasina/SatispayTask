package matteopasina.model.response;

import matteopasina.model.response.body.ResponseBody;

public class Response {
    private int status;
    private String message;
    private ResponseBody responseBody;

    public Response(ResponseBody responseBody, String message, int status) {
        this.responseBody = responseBody;
        this.message = message;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }
}

