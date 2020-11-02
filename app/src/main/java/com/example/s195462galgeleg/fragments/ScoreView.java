package com.example.s195462galgeleg.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.controller.PlayerAdapter;
import com.example.s195462galgeleg.database.AppDatabase;
import com.example.s195462galgeleg.model.Player;
import java.util.ArrayList;
import java.util.List;


public class ScoreView extends Fragment {


    private RecyclerView recyclerView;
    private List<Player> playerList;
    private RecyclerView.Adapter adapter;
    private AppDatabase appDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        playerList = new ArrayList<>();

        appDatabase = Room.databaseBuilder(getActivity(),AppDatabase.class,"playerLog").
                allowMainThreadQueries().
                build();

        List<Player> playerList= appDatabase.playerDAO().getAllPlayers();


        adapter = new PlayerAdapter(this,playerList);
        recyclerView.setAdapter(adapter);


        return view;
    }
}