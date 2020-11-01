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
import com.example.s195462galgeleg.model.Player;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    private Button getStarted;
    private EditText name;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getStarted = findViewById(R.id.start_button);
        name = findViewById(R.id.editTextTextPersonName);

        // insert database to recycler view
        appDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"playList")
                .allowMainThreadQueries().build();

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);


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
                    //appDatabase.playerDAO().insertAll(new Player(str,formattedDate));
                    Player player = new Player(str, formattedDate);
                    InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
                    insertAsyncTask.execute(player);
                }
            }
        });

    }


    class InsertAsyncTask extends AsyncTask<Player, Void, Void> {

        @Override
        protected Void doInBackground(Player... players) {

            AppDatabase.getInstance(getApplicationContext())
                    .playerDAO()
                    .insertAll(players[0]);

            return null;
        }
    }

}