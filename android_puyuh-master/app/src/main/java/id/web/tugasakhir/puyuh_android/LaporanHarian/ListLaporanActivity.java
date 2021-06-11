package id.web.tugasakhir.puyuh_android.LaporanHarian;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.web.tugasakhir.puyuh_android.MenuActivity;
import id.web.tugasakhir.puyuh_android.R;
import id.web.tugasakhir.puyuh_android.RestAPI.ApiClient;
import id.web.tugasakhir.puyuh_android.helper.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListLaporanActivity extends AppCompatActivity {

    private LaporanAdapter adapter;
    private RecyclerView recyclerView;
    Button btnAdd, btnBackMenu;
    ProgressDialog progressDoalog;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        context = getApplicationContext();

        //toPendapatan
        btnAdd = findViewById(R.id.btn_addLaporan);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAdd = new Intent(ListLaporanActivity.this, LaporanActivity.class);
                startActivity(toAdd);
            }
        });

        btnBackMenu = findViewById(R.id.btnBack_listLaporan);
        btnBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toList = new Intent(ListLaporanActivity.this, MenuActivity.class);
                startActivity(toList);
            }
        });

        progressDoalog = new ProgressDialog(ListLaporanActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        Toast.makeText(context, preference.getString("token",null), Toast.LENGTH_SHORT).show();
        LaporanService pendapatanService = ServiceGenerator.createService(LaporanService.class, "Bearer "+preference.getString("token",null));
        retrofit2.Call<List<LaporanData>> call = pendapatanService.getAllLaporan();
        call.enqueue(new Callback<List<LaporanData>>() {
            @Override
            public void onResponse(retrofit2.Call<List<LaporanData>> call, Response<List<LaporanData>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());

            }

            @Override
            public void onFailure(Call<List<LaporanData>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(ListLaporanActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<LaporanData> laporanDataList) {
        recyclerView = findViewById(R.id.laporanRecyclerView);
        adapter = new LaporanAdapter(laporanDataList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
