package id.web.tugasakhir.puyuh_android.models;

public class LoginResponse {
    public String token,token_type;

    public LoginResponse(String token, String token_type) {
        this.token = token;
        this.token_type = token_type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
