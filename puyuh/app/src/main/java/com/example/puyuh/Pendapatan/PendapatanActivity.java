package id.web.tugasakhir.puyuh_android.Pendapatan;

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

public class PendapatanActivity extends AppCompatActivity {
    private PendapatanAdapter adapter;
    private RecyclerView recyclerView;
    Button btnAdd, btnBack;
    ProgressDialog progressDoalog;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendapatan);

        context = getApplicationContext();
        //toPendapatan
        btnAdd = findViewById(R.id.btn_addPendapatan);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAdd = new Intent(PendapatanActivity.this, LaporanPendapatanActivity.class);
                startActivity(toAdd);
            }
        });

        btnBack = findViewById(R.id.btnBack_listPendapatan);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMenu = new Intent(PendapatanActivity.this, MenuActivity.class);
                startActivity(toMenu);
            }
        });

        progressDoalog = new ProgressDialog(PendapatanActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        Toast.makeText(context, preference.getString("token",null), Toast.LENGTH_SHORT).show();
        PendapatanService pendapatanService = ServiceGenerator.createService(PendapatanService.class, "Bearer "+preference.getString("token",null));
        retrofit2.Call<List<PendapatanData>> call = pendapatanService.getAllPendapatan();
        call.enqueue(new Callback<List<PendapatanData>>() {
            @Override
            public void onResponse(retrofit2.Call<List<PendapatanData>> call, Response<List<PendapatanData>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<PendapatanData>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(PendapatanActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<PendapatanData> pendapatanDataList) {
        recyclerView = findViewById(R.id.pendapatanRecyclerView);
        adapter = new PendapatanAdapter(pendapatanDataList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
