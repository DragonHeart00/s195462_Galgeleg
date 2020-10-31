package com.example.s195462galgeleg.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.controller.PlayerAdapter;
import com.example.s195462galgeleg.model.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ScoreFragment extends Fragment {


    private RecyclerView recyclerView;
    private List<Player> playerList;
    private RecyclerView.Adapter adapter;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewid);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        playerList = new ArrayList<>();

        // when we have a fragment
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());


        // Add name and date to recyclerView
        String gemtTekst = sharedPreferences.getString("name", "Ingen gemt editText fundet");
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        Player player1 = new Player(gemtTekst,formattedDate);


        playerList.add(player1);






        adapter = new PlayerAdapter(this,playerList);
        recyclerView.setAdapter(adapter);


        return view;
    }
}