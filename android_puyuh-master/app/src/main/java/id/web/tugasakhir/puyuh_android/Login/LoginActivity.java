package id.web.tugasakhir.puyuh_android.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import id.web.tugasakhir.puyuh_android.MenuActivity;
import id.web.tugasakhir.puyuh_android.R;
import id.web.tugasakhir.puyuh_android.RestAPI.ApiClient;
import id.web.tugasakhir.puyuh_android.helper.ServiceGenerator;
import id.web.tugasakhir.puyuh_android.models.LoginRequest;
import id.web.tugasakhir.puyuh_android.models.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonSignIn;
    private String emailPattern;
    private TokenManager tokenManager;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        tokenManager = new TokenManager(getApplicationContext());
        editTextEmail = (EditText) findViewById(R.id.editTextLoginEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextLoginPass);
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        buttonSignIn = (Button) findViewById(R.id.btnLoginSubmit);

//        buttonSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    if(editTextEmail.getText().toString().isEmpty()) {
//                        Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
//                    }else {
//                        if (editTextEmail.getText().toString().trim().matches(emailPattern)) {
//                            userSignIn();
//                            Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//            }
//        });
    }

//    private void userSignIn() {
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Signing Up...");
//        progressDialog.show();
//
//        final String email = editTextEmail.getText().toString().trim();
//        final String password = editTextPassword.getText().toString().trim();
//
//        JWTTokenService service = ApiClient.getRetrofitInstance().create(JWTTokenService.class);
//
//        Call<JWTToken> call = service.loginUser(email, password);
//
//        call.enqueue(new Callback<JWTToken>() {
//            @Override
//            public void onResponse(Call<JWTToken> call, Response<JWTToken> response) {
//                JWTToken jwtToken = response.body();
////                tokenManager.createSession(email,jwtToken.getToken());
////
////                System.out.println(jwtToken.getToken());
////
////                Intent toLaporan = new Intent(LoginActivity.this, MenuActivity.class);
////                startActivity(toLaporan);
//                tokenManager.createSession(email,jwtToken.getToken());
//                Intent i = new Intent(getApplicationContext(),MenuActivity.class);
//                startActivity(i);
//
//            }
//
//            @Override
//            public void onFailure(Call<JWTToken> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//    }

    public void handleLoginClick(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        doLogin();
    }

    private void doLogin() {
        JWTTokenService service = ServiceGenerator.createService(JWTTokenService.class);
        LoginRequest loginRequest = new LoginRequest(email,password);
        Call<LoginResponse> call = service.doLogin(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preference.edit();
                    editor.putString("token",response.body().getToken());
                    editor.apply();
                    Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                    startActivity(i);
                }else{
//                    ApiError error = ErrorUtils.parseError(response);
//                    Toast.makeText(MainActivity.this, error.getError().getEmail().get(0), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Gagal Koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
