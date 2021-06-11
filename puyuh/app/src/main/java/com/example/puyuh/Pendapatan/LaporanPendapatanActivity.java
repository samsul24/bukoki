package id.web.tugasakhir.puyuh_android.Pendapatan;

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

import id.web.tugasakhir.puyuh_android.LaporanHarian.LaporanService;
import id.web.tugasakhir.puyuh_android.R;
import id.web.tugasakhir.puyuh_android.RestAPI.ApiClient;
import id.web.tugasakhir.puyuh_android.helper.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanPendapatanActivity extends AppCompatActivity {
    private EditText editTelur, editHarga;
    private TextView textTotal, textDate;
    private Button btnSubmitPendapatan, btnBack;
    private String nama;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pendapatan);
        context = getApplicationContext();
        Date today = new Date();
        editTelur = findViewById(R.id.edit_telur);
        editHarga = findViewById(R.id.edit_harga);
        textDate = findViewById(R.id.textDate);
        textTotal = findViewById(R.id.textTotal);
        textDate.setText(today + "");
        btnSubmitPendapatan = findViewById(R.id.btn_submitPendapatan);
        btnBack = findViewById(R.id.btnBack_addPendapatan);

        btnSubmitPendapatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toList = new Intent(LaporanPendapatanActivity.this, PendapatanActivity.class);
                startActivity(toList);
            }
        });
    }

    private void sendData() {
        String tanggal = dateFormat.format(date);
        String jmlTelur = editTelur.getText().toString().trim();
        String jmlHarga = editHarga.getText().toString().trim();
        String totalHarga = Integer.parseInt(jmlTelur) * Integer.parseInt(jmlHarga) + "";
        textTotal.setText(totalHarga);

        String idUser = "1";
        String satuan = "kg";

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        Toast.makeText(context, preference.getString("token",null), Toast.LENGTH_SHORT).show();
        PendapatanService pendapatanService = ServiceGenerator.createService(PendapatanService.class, "Bearer "+preference.getString("token",null));
        Call<PendapatanData> call = pendapatanService.addPendapatan(
                idUser,
                tanggal,
                jmlHarga,
                jmlTelur,
                satuan,
                totalHarga
        );
        call.enqueue(new Callback<PendapatanData>() {
            @Override
            public void onResponse(Call<PendapatanData> call, Response<PendapatanData> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LaporanPendapatanActivity.this, "Gagal ditambahkan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PendapatanData> call, Throwable t) {
                Toast.makeText(LaporanPendapatanActivity.this, "berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                Intent toList = new Intent(LaporanPendapatanActivity.this, PendapatanActivity.class);
                startActivity(toList);
            }
        });

    }
}