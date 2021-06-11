package id.web.tugasakhir.puyuh_android.Login;

import id.web.tugasakhir.puyuh_android.models.LoginRequest;
import id.web.tugasakhir.puyuh_android.models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JWTTokenService {
    @POST("/api/loginAndroid")
    Call<LoginResponse> doLogin(@Body LoginRequest loginRequest);
}
