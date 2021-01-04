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
import com.example.s195462galgeleg.startGame.GetStartD;
import com.example.s195462galgeleg.startGame.GetStartF;
import com.example.s195462galgeleg.startGame.GetStartT;

public class HomeView extends Fragment {
    private Button getStarted, dyr_button, frugter_button;
    private TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getStarted = view.findViewById(R.id.get_started);
        name = view.findViewById(R.id.name_of_player);
        dyr_button = view.findViewById(R.id.dyr_id);
        frugter_button = view.findViewById(R.id.frugter_id);


        //todo: hint navn fra firebase


        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GetStartT.class);
            //    intent.putExtra("name",txtname);
                startActivity(intent);
            }
        });


        dyr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GetStartD.class);

                startActivity(intent);
            }
        });

        frugter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GetStartF.class);
                startActivity(intent);
            }
        });







        return view;
    }
}