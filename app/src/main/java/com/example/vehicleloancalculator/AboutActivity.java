package com.example.vehicleloancalculator; // <-- match your package

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnBack = findViewById(R.id.btnBack);

        // Back button returns to MainActivity
        btnBack.setOnClickListener(v -> finish());
    }
}
