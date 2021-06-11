package com.example.voivenote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String STATE_RESULT = null;
    EditText edtOutput;
    ImageButton imgbtn;
    private Button btnSubmit;
    private TextView txtHasil;



    protected static final int RESULT_SPEECH_1 = 1;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, txtHasil.getText().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtOutput = (EditText) findViewById(R.id.editText);
        imgbtn = (ImageButton) findViewById(R.id.imageButton);
        btnSubmit = findViewById(R.id.btn_submit);


        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToastMessage("Masukkan suara Anda : ");
                String ID_BhsIndonesia = "id";
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, ID_BhsIndonesia);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, ID_BhsIndonesia);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, ID_BhsIndonesia);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Masukkan Suara ");
                try {
                    startActivityForResult(intent, RESULT_SPEECH_1);
                } catch (ActivityNotFoundException a) {

                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edtOutput.getText().toString();
                Intent kirim = new Intent(MainActivity.this, HasilActivity.class);
                kirim.putExtra("texthasil",text);
                startActivity(kirim);
            }
        });

}

    protected void onActivityResult(int requstCode, int resultCode, Intent data) {
        super.onActivityResult(requstCode, resultCode, data);

        switch (requstCode) {
            case RESULT_SPEECH_1: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> MasukanSuaraAnda = data.getStringArrayListExtra((RecognizerIntent.EXTRA_RESULTS));
                    edtOutput.setText(MasukanSuaraAnda.get(0));
                }
                if (resultCode == RecognizerIntent.RESULT_AUDIO_ERROR) {
                    showToastMessage("Audio Bermasalah");
                } else if (resultCode == RecognizerIntent.RESULT_CLIENT_ERROR) {
                    showToastMessage("Client Bermasalah");
                } else if (resultCode == RecognizerIntent.RESULT_NETWORK_ERROR) {
                    showToastMessage("Network Bermasalah");
                } else if (resultCode == RecognizerIntent.RESULT_NO_MATCH) {
                    showToastMessage("Perangkat Tidak Cocok");
                } else if (resultCode == RecognizerIntent.RESULT_SERVER_ERROR) {
                    showToastMessage("Server Bermasalah");
                }
                break;

            }
        }
    }

    void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    public void gotoHasil(View view){
        Intent intent= new Intent(this, HasilActivity.class);
        startActivity(intent);
    }

}
