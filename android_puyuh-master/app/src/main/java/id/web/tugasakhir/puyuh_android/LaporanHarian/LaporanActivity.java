package id.web.tugasakhir.puyuh_android.LaporanHarian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.web.tugasakhir.puyuh_android.Login.JWTTokenService;
import id.web.tugasakhir.puyuh_android.Login.TokenManager;
import id.web.tugasakhir.puyuh_android.MenuActivity;
import id.web.tugasakhir.puyuh_android.R;
import id.web.tugasakhir.puyuh_android.RestAPI.ApiClient;
import id.web.tugasakhir.puyuh_android.helper.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanActivity extends AppCompatActivity {
  private EditText editKandang, editTelur, editKematian;
  private TextView textDate;
  private Button btnSubmitLaporan, btnBackList;
  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  Date date = new Date();
  private TokenManager tokenManager;
  private Context context;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_laporan);
    Date today = new Date();
    editKandang = findViewById(R.id.edit_kandang);
    editTelur = findViewById(R.id.edit_laporanTelur);
    editKematian = findViewById(R.id.edit_laporanKematian);
    textDate = findViewById(R.id.textTanggalLaporan);
    textDate.setText(today + "");
    btnSubmitLaporan = findViewById(R.id.btn_submitLaporan);
    btnBackList = findViewById(R.id.btnBack_addLaporan);
    context = getApplicationContext();

    tokenManager = new TokenManager(getApplicationContext());
    System.out.println("ini token laporan = " + tokenManager.getKeyJwtToken());

    btnSubmitLaporan.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        sendData();
      }
    });
    btnBackList.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent toList = new Intent(LaporanActivity.this, ListLaporanActivity.class);
        startActivity(toList);
      }
    });
  }

  private void sendData() {
    String tanggal = dateFormat.format(date);
    String noKandang = editKandang.getText().toString().trim();
    String jmlTelur = editTelur.getText().toString().trim();
    String jmlKematian = editKematian.getText().toString().trim();
    String idUser = 1 + "";

    SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
    Toast.makeText(context, preference.getString("token",null), Toast.LENGTH_SHORT).show();
    LaporanService laporanService = ServiceGenerator.createService(LaporanService.class, "Bearer "+preference.getString("token",null));
    Call<LaporanData> callLaporan = laporanService.addLaporan(
            idUser,
            noKandang,
            tanggal,
            jmlTelur,
            jmlKematian
    );
    callLaporan.enqueue(new Callback<LaporanData>() {
      @Override
      public void onResponse(Call<LaporanData> call, Response<LaporanData> response) {
        if (response.isSuccessful()) {
          Toast.makeText(LaporanActivity.this, "Gagal ditambahkan", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<LaporanData> call, Throwable t) {
        Toast.makeText(LaporanActivity.this, "Data Berhasil ditambahkan", Toast.LENGTH_SHORT).show();
        Intent toList = new Intent(LaporanActivity.this, ListLaporanActivity.class);
        startActivity(toList);
      }
    });
  }
}