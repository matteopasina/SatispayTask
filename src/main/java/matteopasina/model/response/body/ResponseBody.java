package matteopasina.model.response.body;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;


public class ResponseBody {

    @SerializedName("authentication_key")
    private AuthenticationKey authenticationKey;

    @SerializedName("signature")
    private Signature signature;

    @SerializedName("signed_string")
    private String signedString;

    public static ResponseBody jsonToObj(String json) throws JsonSyntaxException {
        Gson mapper = new Gson();
        return mapper.fromJson(json, ResponseBody.class);
    }

    public AuthenticationKey getAuthenticationKey() {
        return authenticationKey;
    }

    public void setAuthenticationKey(AuthenticationKey authenticationKey) {
        this.authenticationKey = authenticationKey;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public String getSignedString() {
        return signedString;
    }

    public void setSignedString(String signedString) {
        this.signedString = signedString;
    }
}
