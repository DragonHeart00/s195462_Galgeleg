package com.example.s195462galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.s195462galgeleg.R;

public class WinnerActivity extends AppCompatActivity {


    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        textView=findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();

        String data = bundle.getString("AntalForkerteBogstaver");
        textView.setText("Antal Forkerte Bogstaver var "+data + " Bogstaver");

    }
}