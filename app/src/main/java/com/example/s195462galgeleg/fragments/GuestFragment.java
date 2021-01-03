package com.example.s195462galgeleg.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.activities.GetStartActivity;
import com.example.s195462galgeleg.activities.WelcomeActivity;

public class GuestFragment extends Fragment {
    private Button log_ind;
    private TextView textview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_guest, container, false);


        textview = view.findViewById(R.id.score_id);
        log_ind=view.findViewById(R.id.sign_in_id);




        log_ind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}