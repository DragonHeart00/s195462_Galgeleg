package com.example.s195462galgeleg.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.activities.GetStartActivity;


public class HomeView extends Fragment {
    private Button getStarted;
    private TextView name;
    private Animation button_animation ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getStarted = view.findViewById(R.id.get_started);



        //load animation
        button_animation= AnimationUtils.loadAnimation(getActivity(), R.anim.button_anim);


        //passing animation
        getStarted.startAnimation(button_animation);


        //import font
        Typeface MMedium = Typeface.createFromAsset(getActivity().getAssets(),"fonts/MMedium.ttf");



        // customize font
        getStarted.setTypeface(MMedium);


       /* name = view.findViewById(R.id.name);
        Intent intent=getActivity().getIntent();
        String txtname = intent.getStringExtra("name");
        name.setText("Velkommen "+txtname + " til Galgeleg");

        */



        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GetStartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            //    intent.putExtra("name",txtname);
                startActivity(intent);
            }
        });


        return view;
    }
}