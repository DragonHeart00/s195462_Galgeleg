package com.example.s195462galgeleg.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.s195462galgeleg.MainActivity;
import com.example.s195462galgeleg.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
public class Register extends AppCompatActivity {

    private Button registerButton;
    private EditText userName, password, email;
    private TextView loginBtn;
    private ProgressBar progressBar;
    private FirebaseAuth myAuth;

    private FirebaseFirestore firestore;
    private String plyerID;


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
        firestore=FirebaseFirestore.getInstance();

       /* Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        */

        //check if player already login or not
        if (myAuth.getCurrentUser() != null){
            startActivity(new Intent(Register.this, MainActivity.class));
            finish();
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myEmail = email.getText().toString().trim();
                String myPassword = password.getText().toString().trim();
                String playerName = userName.getText().toString();
                String playerScore = "0";

                if (TextUtils.isEmpty(myEmail)){
                    email.setError("Email is not valid");
                    return;
                }

                if (TextUtils.isEmpty(myPassword)){
                    password.setError("Password is not valid");
                    return;
                }if (TextUtils.isEmpty(playerName)){
                    userName.setError("not valid");
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

                            plyerID = myAuth.getCurrentUser().getUid();

                            DocumentReference documentReference = firestore.collection("players").document(plyerID);
                            Map<String, Object> players = new HashMap<>();
                            players.put("name", playerName);
                            players.put("email", myEmail);
                            players.put("score", playerScore);
                            documentReference.set(players).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG","onSuccess: User profile is created for " +plyerID );
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG","onFailure: User profile is not created " +e.toString() );
                                }
                            });





                            Intent intent = new Intent(Register.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                           /* Player player = new Player(myEmail, formattedDate);
                            PlayerViewModel playerViewModel = new PlayerViewModel(getApplication());
                            playerViewModel.insert(player);

                            */

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
                finish();
            }
        });
    }
}