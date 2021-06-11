package id.web.tugasakhir.puyuh_android.AboutUs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import id.web.tugasakhir.puyuh_android.MenuActivity;
import id.web.tugasakhir.puyuh_android.R;

public class AboutActivity extends AppCompatActivity {
    public Button btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        btnBack = findViewById(R.id.btnBack_about);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMenu = new Intent(AboutActivity.this, MenuActivity.class);
                startActivity(toMenu);
            }
        });
    }
}
