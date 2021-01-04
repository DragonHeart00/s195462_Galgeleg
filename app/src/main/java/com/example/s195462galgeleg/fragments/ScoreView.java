package com.example.s195462galgeleg.fragments;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.activities.WelcomeActivity;
import com.example.s195462galgeleg.model.Player;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class ScoreView extends Fragment {


   private RecyclerView recyclerView;

   private Button logout;

   private FirestoreRecyclerAdapter adapter;

   private FirebaseFirestore firebaseFirestore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewid);
        logout=view.findViewById(R.id.delete_all);





        firebaseFirestore = FirebaseFirestore.getInstance();


        Query query = firebaseFirestore.collection("players");

        FirestoreRecyclerOptions<Player> options = new FirestoreRecyclerOptions.Builder<Player>()
                .setQuery(query, Player.class)
                .build();


        adapter = new FirestoreRecyclerAdapter<Player, PlayerViewHolder>(options) {
            @NonNull
            @Override
            public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent , false);
                return new PlayerViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PlayerViewHolder holder, int position, @NonNull Player model) {
                holder.name.setText("brugernavn: " + model.getName());
                holder.score.setText("score: " + model.getScore());
            }
        };
        recyclerView .setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });




        return view;
    }



    private class PlayerViewHolder extends RecyclerView.ViewHolder{

        private TextView name,score;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.player_name);
            score = itemView.findViewById(R.id.score);
                    }











    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
