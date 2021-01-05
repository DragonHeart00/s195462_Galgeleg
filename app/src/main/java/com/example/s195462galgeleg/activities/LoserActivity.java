package com.example.s195462galgeleg.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.s195462galgeleg.MainActivity;
import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.startGame.GetStartT;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class LoserActivity extends AppCompatActivity {

    private TextView word, name , score;
    private Animation button_animation ;
    private Button try_again;
    private FirebaseAuth myAuth;
    private FirebaseFirestore firestore;
    private String plyerID;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loser);
        word=findViewById(R.id.get_word);
        try_again=findViewById(R.id.try_again);
        name = findViewById(R.id.player_name_id_lose);
        score = findViewById(R.id.txt_lo);
        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("data");
        word.setText("Ordet var "+"'" +data+ "'" + " ):");
        //load animation
        button_animation= AnimationUtils.loadAnimation(this, R.anim.button_anim);


        //passing animation
        try_again.startAnimation(button_animation);


        //import font
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");



        myAuth =FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        firebaseUser = myAuth.getCurrentUser();



        if (firebaseUser != null) {
            //database

            plyerID = myAuth.getCurrentUser().getUid();


            DocumentReference documentReference = firestore.collection("players").document(plyerID);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                    name.setText(value.getString("name") + ". Du har tabte");
                    score.setText("Din Score: "+value.getString("score") );
//                    int x = (int)  value.get("score");
//                    score_text.setText( x + "");
                }
            });


        }




        try_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoserActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //TODO: show name of player in GetStartActivity
                startActivity(intent);
                finish();
            }
        });





    }
}