package com.example.s195462galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.s195462galgeleg.MainActivity;
import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.database.AppDatabase;
import com.example.s195462galgeleg.database.PlayerViewModel;
import com.example.s195462galgeleg.model.Player;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    private Button getStarted;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getStarted = findViewById(R.id.start_button);
        name = findViewById(R.id.editTextTextPersonName);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);


            getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = name.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    name.setError("savnet mig!");
                    return;
                }else {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    intent.putExtra("name",str);
                    startActivity(intent);
                    //must change
                    Player player = new Player(str, formattedDate);
                    PlayerViewModel playerViewModel = new PlayerViewModel(getApplication());
                    playerViewModel.insert(player);
                }
            }
        });
    }

}