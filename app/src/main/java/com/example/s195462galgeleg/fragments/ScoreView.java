package com.example.s195462galgeleg.fragments;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.activities.GetStartActivity;
import com.example.s195462galgeleg.activities.WelcomeActivity;
import com.example.s195462galgeleg.controller.PlayerAdapter;
import com.example.s195462galgeleg.database.AppDatabase;
import com.example.s195462galgeleg.database.PlayerViewModel;
import com.example.s195462galgeleg.model.Player;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;


public class ScoreView extends Fragment {


   private RecyclerView recyclerView;
   private PlayerViewModel playerViewModel;
   private Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        PlayerAdapter playerAdapter = new PlayerAdapter();
        recyclerView.setAdapter(playerAdapter);

        logout=view.findViewById(R.id.delete_all);
       /* deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerViewModel.deleteAll();

            }
        });

        */

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });



        playerViewModel = new ViewModelProvider(getActivity()).get(PlayerViewModel.class);

        playerViewModel.getAllPlayer().observe(getActivity(), new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                //update recyclerView
                playerAdapter.setPlayerList(players);
            }
        });

        return view;
    }


}