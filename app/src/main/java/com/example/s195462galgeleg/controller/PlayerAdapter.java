package com.example.s195462galgeleg.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.fragments.ScoreView;
import com.example.s195462galgeleg.model.Player;

import java.util.List;


public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private ScoreView scoreView;
    private List<Player> lists;

    public PlayerAdapter(ScoreView scoreView, List<Player> lists) {
        this.scoreView = scoreView;
        this.lists = lists;
    }



    @NonNull
    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.ViewHolder holder, int position) {
        Player player = lists.get(position);
        holder.name.setText(player.getName());
        holder.score.setText(player.getScore());
        holder.date.setText(player.getDate());


    }




    @Override
    public int getItemCount() {
        return lists.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private  TextView score;
        private TextView date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.player_name);
            score = itemView.findViewById(R.id.score);
            date = itemView.findViewById(R.id.date);
        }



    }

}
