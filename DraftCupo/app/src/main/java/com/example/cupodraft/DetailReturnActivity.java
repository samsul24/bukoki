package com.example.cupodraft;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cupodraft.api.helper.ServiceGenerator;
import com.example.cupodraft.api.model.KembaliResponse;
import com.example.cupodraft.api.model.PinjamResponse;
import com.example.cupodraft.api.services.ApiInterface;
import com.example.cupodraft.ui.FailActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailReturnActivity extends AppCompatActivity {
    TextView idKembali, tglKembali, txtStatus, txtDenda, txtTerlambat, inDenda, inTerlambat, txtWait;
    String id_prod, id_cust, id_kembali, tgl;
    ProgressBar progressBar;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_return);
        idKembali = findViewById(R.id.inputIdKembali);
        tglKembali = findViewById(R.id.inputTglKembali);
        txtStatus = findViewById(R.id.inputStatus);
        txtDenda = findViewById(R.id.textView15);
        txtTerlambat = findViewById(R.id.textView14);
        inDenda = findViewById(R.id.inputDenda);
        inTerlambat = findViewById(R.id.inputTerlambat);
        progressBar = findViewById(R.id.progressBar);
        constraintLayout = findViewById(R.id.layout_return);
        txtWait = findViewById(R.id.wait);

        SharedPreferences preferences = getSharedPreferences("data_login", Context.MODE_PRIVATE);
        id_cust = preferences.getString("id_customer","");
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
//        id_produk = pref.getString("id_produk","");
        id_prod = getIntent().getStringExtra("id_produk");
        Log.d("recyctest", "Test customer: "+id_cust);
        Log.d("recyctest", "Test produk: "+id_prod);
        getKembali();
    }

    private void getKembali() {
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<KembaliResponse> call = service.getPengembalian(id_cust, id_prod);
        call.enqueue(new Callback<KembaliResponse>() {
            @Override
            public void onResponse(Call<KembaliResponse> call, Response<KembaliResponse> response) {
                if (response.body().getData().length == 0) {
                    Toast.makeText(DetailReturnActivity.this, R.string.gagal, Toast.LENGTH_SHORT).show();
                    errorDialog();
                } else {
                    id_kembali = response.body().getData()[0].getId_kembali();
                    Log.e("keshav", "kembaliResponse 1 --> " + id_kembali);
                    if (response.body().getData()[0].getIs_acc() != null) {
                        constraintLayout.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        txtWait.setVisibility(View.GONE);
                        String id = response.body().getData()[0].getId_kembali();
                        String tanggalKembali = response.body().getData()[0].getTanggal_kembali();
                        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            tgl = dateFormat.format(df.parse(tanggalKembali));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String terlambat = response.body().getData()[0].getTerlambat();
                        String denda = response.body().getData()[0].getDenda();
                        if (!terlambat.equals("0")) {
                            txtDenda.setVisibility(View.VISIBLE);
                            txtTerlambat.setVisibility(View.VISIBLE);
                            inDenda.setVisibility(View.VISIBLE);
                            inTerlambat.setVisibility(View.VISIBLE);
                            txtStatus.setText(R.string.terlambat);
                            inTerlambat.setText(terlambat + getString(R.string.hari));
                            inDenda.setText("Rp. " + denda);
                        }
                        tglKembali.setText(tgl);
                        idKembali.setText(id);
                    } else {
                        constraintLayout.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                        txtWait.setVisibility(View.VISIBLE);
                        getKembali();
//                        recreate();
                    }
                }
            }

            @Override
            public void onFailure(Call<KembaliResponse> call, Throwable t) {
                Toast.makeText(DetailReturnActivity.this, R.string.gagal_koneksi, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void errorDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialogTheme);
        builder.setTitle(R.string.gagal_memindai_data);
        builder.setIcon(R.drawable.ic_cup1);
        builder.setMessage(R.string.proses_gagal);
        builder.setCancelable(true);

        builder.setPositiveButton(
                R.string.coba_lagi,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        startActivity(new Intent(DetailReturnActivity.this, ScanReturnActivity.class));
                        finish();
                    }
                });

        builder.setNegativeButton(
                R.string.batal,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        startActivity(new Intent(DetailReturnActivity.this, MenuActivity.class));
                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void handleHome(View view) {
        startActivity(new Intent(DetailReturnActivity.this, NavActivity.class));
        finish();
    }
}