package com.example.s195462galgeleg.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.activities.GetStartActivity;


public class Fragment_1 extends Fragment {

    private Button getStarted;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_1, container, false);

        getStarted = view.findViewById(R.id.get_started);




        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GetStartActivity.class);
                startActivity(intent);
            }
        });



       return view;
    }

}