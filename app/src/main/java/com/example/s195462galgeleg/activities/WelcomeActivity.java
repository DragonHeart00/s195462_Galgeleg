package com.example.s195462galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.s195462galgeleg.MainActivity;
import com.example.s195462galgeleg.R;

public class WelcomeActivity extends AppCompatActivity {

    private Button getStarted;
    private EditText name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getStarted = findViewById(R.id.start_button);
        name = findViewById(R.id.editTextTextPersonName);


        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = name.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    name.setError("missed me!");
                    return;
                }else {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    intent.putExtra("name",str);
                    startActivity(intent);
                }

            }
        });
    }
}