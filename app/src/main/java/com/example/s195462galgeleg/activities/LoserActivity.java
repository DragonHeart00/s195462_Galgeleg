package com.example.s195462galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.s195462galgeleg.R;

public class LoserActivity extends AppCompatActivity {

    TextView ord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loser);
        ord=findViewById(R.id.get_word);

        Bundle bundle = getIntent().getExtras();

        String data = bundle.getString("data");
        ord.setText("Ordet var "+"'" +data+ "'" + " ):");


    }
}