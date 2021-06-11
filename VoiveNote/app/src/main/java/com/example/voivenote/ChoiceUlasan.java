package com.example.voivenote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ChoiceUlasan extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }
    public void textTyping(View view) {
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
