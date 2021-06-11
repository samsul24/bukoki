package id.web.tugasakhir.puyuh_android.Login;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int Mode = 0;
    private static final String REFNAME = "JWTTOKEN";
        private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_JWT_TOKEN = "jwttoken";
    private Context context;

    public TokenManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(REFNAME,Mode);
        editor = sharedPreferences.edit();
    }

    public void createSession(String email, String jwtValue){
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_JWT_TOKEN, jwtValue);
        editor.commit();
    }

    public String getKeyJwtToken() {
        String token = sharedPreferences.getString(KEY_JWT_TOKEN, "");
        return token;
    }
}
