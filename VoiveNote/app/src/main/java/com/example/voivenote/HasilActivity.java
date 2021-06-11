package com.example.voivenote;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HasilActivity  extends AppCompatActivity {
    private TextView tvhasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        tvhasil = findViewById(R.id.textView);

        Intent terima = getIntent();
        String hasilText= terima.getStringExtra("texthasil");
        tvhasil.setText(hasilText);


    }
}
