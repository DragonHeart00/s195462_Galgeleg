package com.example.s195462galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.s195462galgeleg.R;

public class WinnerActivity extends AppCompatActivity {

    private Button play_again;
    private TextView wrongChar, score;
    private Animation button_animation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        wrongChar=findViewById(R.id.textView);
        score = findViewById(R.id.score_text);
        play_again=findViewById(R.id.play_again);


        //load animation
        button_animation= AnimationUtils.loadAnimation(this, R.anim.button_anim);


        //passing animation
        play_again.startAnimation(button_animation);


        //import font
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");

        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  =new Intent(WinnerActivity.this, GetStartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //TODO: show name of player in GetStartActivity
                startActivity(intent);
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();

        String data = bundle.getString("AntalForkerteBogstaver");
        String my_score = bundle.getString("your_score");
        wrongChar.setText("Antal Forkerte Bogstaver var "+data + " Bogstaver");
        score.setText("du har fået  "+my_score + " point");
    }
}