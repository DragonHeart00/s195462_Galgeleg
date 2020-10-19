package com.example.s195462galgeleg.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.controller.PlayerAdapter;
import com.example.s195462galgeleg.model.Player;

import java.util.ArrayList;
import java.util.List;


public class ScoreFragment extends Fragment {


    private RecyclerView recyclerView;
    private List<Player> playerList;
    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewid);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        playerList = new ArrayList<>();


        Player player1 = new Player("Amer", "38400", "10.Oct.2020");
        Player player2 = new Player("Mark", "34000", "12.May.2020");

        playerList.add(player1);
        playerList.add(player2);







        adapter = new PlayerAdapter(this,playerList);
        recyclerView.setAdapter(adapter);


        return view;
    }
}