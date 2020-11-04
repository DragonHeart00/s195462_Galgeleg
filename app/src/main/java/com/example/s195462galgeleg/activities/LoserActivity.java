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

public class LoserActivity extends AppCompatActivity {

    private TextView word;
    private Animation button_animation ;
    private Button try_again;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loser);
        word=findViewById(R.id.get_word);
        try_again=findViewById(R.id.try_again);
        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("data");
        word.setText("Ordet var "+"'" +data+ "'" + " ):");
        //load animation
        button_animation= AnimationUtils.loadAnimation(this, R.anim.button_anim);


        //passing animation
        try_again.startAnimation(button_animation);


        //import font
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");


        try_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoserActivity.this,GetStartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //TODO: show name of player in GetStartActivity
                startActivity(intent);
                finish();
            }
        });





    }
}