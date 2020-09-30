package matteopasina.model.response.body;

import com.google.gson.annotations.SerializedName;

public class AuthenticationKey {

    @SerializedName("access_key")
    private String accessKey;

    @SerializedName("customer_uid")
    private String customerUid;

    @SerializedName("key_type")
    private String keyType;

    @SerializedName("auth_type")
    private String authType;

    @SerializedName("role")
    private String role;

    @SerializedName("enable")
    private Boolean enable;

    @SerializedName("insert_date")
    private String insertDate;

    @SerializedName("version")
    private Integer version;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getCustomerUid() {
        return customerUid;
    }

    public void setCustomerUid(String customerUid) {
        this.customerUid = customerUid;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
