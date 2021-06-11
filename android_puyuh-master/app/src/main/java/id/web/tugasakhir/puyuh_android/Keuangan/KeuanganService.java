package id.web.tugasakhir.puyuh_android.Keuangan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface KeuanganService {
//    @GET("kandang")
//    Call<List<KandangData>> getAllKandang();
//
//    @GET("gaji")
//    Call<List<GajiData>> getAllGaji();

    @GET("keuangan")
    Call<List<KeuanganData>> getAllKeuangan();

}
