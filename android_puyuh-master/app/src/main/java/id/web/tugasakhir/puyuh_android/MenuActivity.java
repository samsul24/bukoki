package id.web.tugasakhir.puyuh_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import id.web.tugasakhir.puyuh_android.AboutUs.AboutActivity;
import id.web.tugasakhir.puyuh_android.Keuangan.KeuanganActivity;
import id.web.tugasakhir.puyuh_android.LaporanHarian.ListLaporanActivity;
import id.web.tugasakhir.puyuh_android.Login.JWTTokenService;
import id.web.tugasakhir.puyuh_android.Pendapatan.PendapatanActivity;
import id.web.tugasakhir.puyuh_android.helper.ServiceGenerator;


public class MenuActivity<TokenManager> extends AppCompatActivity {
    Button btnLaporan, btnPendapatan, btnData, btnKas, btnKeuangan, btnAbout;
    TextView dateNow;
    private TokenManager tokenManager;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        context = getApplicationContext();
//        getMe();

//dateNow
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        dateNow = findViewById(R.id.textHariIni);
        dateNow.setText(formatter.format(date));

        //toLaporan
        btnLaporan = findViewById(R.id.btn_laporan);
        btnLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLaporan = new Intent(MenuActivity.this, ListLaporanActivity.class);
                startActivity(toLaporan);
            }
        });

        //toPendapatan
        btnPendapatan = findViewById(R.id.btn_pendapatan);
        btnPendapatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPendapatan = new Intent(MenuActivity.this, PendapatanActivity.class);
                startActivity(toPendapatan);
            }
        });

        //toKeuangan
        btnKeuangan = findViewById(R.id.btn_keuangan);
        btnKeuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toKeuangan = new Intent(MenuActivity.this, KeuanganActivity.class);
                startActivity(toKeuangan);
            }
        });

        //toAboutUs
        btnAbout = findViewById(R.id.btn_aboutUs);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAbout = new Intent(MenuActivity.this, AboutActivity.class);
                startActivity(toAbout);
            }
        });
    }

    private void getMe() {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        Toast.makeText(context, preference.getString("token",null), Toast.LENGTH_SHORT).show();
        JWTTokenService service = ServiceGenerator.createService(JWTTokenService.class, "Bearer "+preference.getString("token",null));
    }
}
