package matteopasina.model.response.body;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Signature {

    @SerializedName("key_id")
    private String keyId;

    @SerializedName("algorithm")
    private String algorithm;

    @SerializedName("headers")
    private List<String> headers = null;

    @SerializedName("signature")
    private String signature;

    @SerializedName("resign_required")
    private Boolean resignRequired;

    @SerializedName("iteration_count")
    private Integer iterationCount;

    @SerializedName("valid")
    private Boolean valid;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Boolean getResignRequired() {
        return resignRequired;
    }

    public void setResignRequired(Boolean resignRequired) {
        this.resignRequired = resignRequired;
    }

    public Integer getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(Integer iterationCount) {
        this.iterationCount = iterationCount;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}

