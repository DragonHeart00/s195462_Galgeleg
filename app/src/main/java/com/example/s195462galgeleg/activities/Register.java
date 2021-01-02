package com.example.s195462galgeleg.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s195462galgeleg.MainActivity;
import com.example.s195462galgeleg.R;
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

public class Register extends AppCompatActivity {

    private Button registerButton;
    private EditText userName, password, email;
    private TextView loginBtn;
    private ProgressBar progressBar;
    private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = findViewById(R.id.name_id);
        password = findViewById(R.id.password_id);
        email = findViewById(R.id.email_id);
        loginBtn = findViewById(R.id.logid);
        registerButton = findViewById(R.id.register_button);
        progressBar = findViewById(R.id.progressBar);
        myAuth =FirebaseAuth.getInstance();


        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        //check if player already login or not
        if (myAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
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

                myAuth.createUserWithEmailAndPassword(myEmail,myPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
//                          Toast.makeText(getApplicationContext(),"User Created" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            startActivity(intent);
                            Player player = new Player(myEmail, formattedDate);
                            PlayerViewModel playerViewModel = new PlayerViewModel(getApplication());
                            playerViewModel.insert(player);

                        }else {
                            Toast.makeText(Register.this,"User not Created" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }

                    }
                });

            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, WelcomeActivity.class));
            }
        });
    }
}