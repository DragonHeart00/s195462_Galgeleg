package com.example.s195462galgeleg.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.activities.GetStartActivity;


public class HomeFragment extends Fragment {
    private Button getStarted;
    private TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getStarted = view.findViewById(R.id.get_started);
        name = view.findViewById(R.id.name);
        Intent intent=getActivity().getIntent();
        String txtname = intent.getStringExtra("name");
        name.setText("Welcome "+txtname + " to Hangman");



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