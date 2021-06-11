package id.web.tugasakhir.puyuh_android.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JWTToken {

    @SerializedName("token")
    @Expose

    public String token;

    public JWTToken() {
    }

    public JWTToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
