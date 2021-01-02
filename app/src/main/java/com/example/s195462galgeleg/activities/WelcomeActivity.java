package com.example.s195462galgeleg.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s195462galgeleg.MainActivity;
import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.database.AppDatabase;
import com.example.s195462galgeleg.database.PlayerViewModel;
import com.example.s195462galgeleg.model.Player;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    private Button login;
    private EditText email, password;
    private TextView goto_register;
    private Animation button_animation ;
    private ProgressBar progressBar;
    private FirebaseAuth myAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        login = findViewById(R.id.start_button);
        email = findViewById(R.id.email_edit_text);
        password=findViewById(R.id.password_edit_text);
        goto_register = findViewById(R.id.goto_register);
        progressBar = findViewById(R.id.progressBar2);
        //load animation
        button_animation= AnimationUtils.loadAnimation(this, R.anim.button_anim);

        myAuth =FirebaseAuth.getInstance();
        //passing animation
        login.startAnimation(button_animation);


        //import font
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");



        // customize font
        login.setTypeface(MMedium);

        if (myAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        /*
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = email.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    email.setError("savnet mig!");
                    return;
                }else {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.putExtra("name",str);
                    startActivity(intent);
                    //must change
                    Player player = new Player(str, formattedDate);
                    PlayerViewModel playerViewModel = new PlayerViewModel(getApplication());
                    playerViewModel.insert(player);
                }

                progressBar.setVisibility(View.VISIBLE);


            }
        });

         */
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myEmail = email.getText().toString().trim();
                String myPassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(myEmail)){
                    email.setError("Email is not valid");
                    return;
                }

                if (TextUtils.isEmpty(myPassword)){
                    password.setError("Password is not valid");
                    return;
                }

                if (myPassword.length() < 6){
                    password.setError("Password must be >= 6 char");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);



                myAuth.signInWithEmailAndPassword(myEmail, myPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(WelcomeActivity.this,"Log in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            startActivity(intent);
                            Player player = new Player(myEmail, formattedDate);
                            PlayerViewModel playerViewModel = new PlayerViewModel(getApplication());
                            playerViewModel.insert(player);

                        }else {
                            Toast.makeText(WelcomeActivity.this,"Error !!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });






        goto_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, Register.class));
            }
        });
    }

}