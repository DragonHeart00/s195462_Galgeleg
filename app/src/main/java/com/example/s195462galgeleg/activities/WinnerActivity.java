package com.example.s195462galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.s195462galgeleg.R;

public class WinnerActivity extends AppCompatActivity {

    private Button play_again;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        textView=findViewById(R.id.textView);
        play_again=findViewById(R.id.play_again);


        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  =new Intent(WinnerActivity.this, GetStartActivity.class);
                //TODO: show name of player in GetStartActivity
                startActivity(intent);
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();

        String data = bundle.getString("AntalForkerteBogstaver");
        textView.setText("Antal Forkerte Bogstaver var "+data + " Bogstaver");

    }
}