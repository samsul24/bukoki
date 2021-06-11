package id.web.tugasakhir.puyuh_android.Keuangan;

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
import id.web.tugasakhir.puyuh_android.helper.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KeuanganActivity extends AppCompatActivity {
    private KeuanganAdapter adapter;
    private RecyclerView recyclerView;
    Button btnBack;
    ProgressDialog progressDoalog;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keuangan);
        context = getApplicationContext();
        btnBack = findViewById(R.id.btnBack_keuangan);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMenu = new Intent(KeuanganActivity.this, MenuActivity.class);
                startActivity(toMenu);
            }
        });

        progressDoalog = new ProgressDialog(KeuanganActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        Toast.makeText(context, preference.getString("token",null), Toast.LENGTH_SHORT).show();
        KeuanganService keuanganService = ServiceGenerator.createService(KeuanganService.class, "Bearer "+preference.getString("token",null));
        Call<List<KeuanganData>> call = keuanganService.getAllKeuangan();
        call.enqueue(new Callback<List<KeuanganData>>() {
            @Override
            public void onResponse(Call<List<KeuanganData>> call, Response<List<KeuanganData>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<KeuanganData>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(KeuanganActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<KeuanganData> keuanganDataList) {
        recyclerView = findViewById(R.id.keuanganRecyclerView);
        adapter = new KeuanganAdapter(keuanganDataList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
