package com.example.s195462galgeleg.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Delete;
import androidx.room.Room;

import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.controller.PlayerAdapter;
import com.example.s195462galgeleg.database.AppDatabase;
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
    private AppDatabase appDatabase;
    private Button deletePlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewid);
        deletePlayer=view.findViewById(R.id.delete_user);





        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        playerList = new ArrayList<>();



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                playerList = AppDatabase.getInstance(getActivity())
                        .playerDAO()
                        .getAllPlayers();

              //  Log.d(TAG, "run: " + todoList.toString());
            }
        });
        thread.start();
        /*appDatabase = Room.databaseBuilder(getActivity(),AppDatabase.class,"playList").
                allowMainThreadQueries().
                build();

        List<Player> playerList= appDatabase.playerDAO().getAllPlayers();

         */




        adapter = new PlayerAdapter(this,playerList);
        recyclerView.setAdapter(adapter);


        return view;
    }


}