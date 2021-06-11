package id.web.tugasakhir.puyuh_android.Pendapatan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PendapatanService {

    @GET("pendapatanHarian")
    Call<List<PendapatanData>> getAllPendapatan();

    @FormUrlEncoded
    @POST("pendapatanHarian")
    Call<PendapatanData> addPendapatan(
            @Field("id_user") String idUser,
            @Field("tanggal") String tanggal,
            @Field("harga") String jmlHarga,
            @Field("jumlah") String jmlTelur,
            @Field("satuan") String satuan,
            @Field("total") String totalHarga
    );
}
