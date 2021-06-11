package id.web.tugasakhir.puyuh_android.LaporanHarian;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LaporanService {

  @GET("laporanHarian")
  Call<List<LaporanData>> getAllLaporan();

  @FormUrlEncoded
  @POST("laporanHarian")
  Call<LaporanData> addLaporan(
          @Field("id_user") String idUser,
          @Field("no_kandang") String noKandang,
          @Field("tanggal") String tanggal,
          @Field("jumlah_telur") String jmlTelur,
          @Field("jumlah_kematian") String jmlKematian
  );
}