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
import com.example.s195462galgeleg.startGame.GetStartL;
import com.example.s195462galgeleg.startGame.GetStartT;

public class HomeView extends Fragment {
    private Button tilfældigt_button, dyr_button, frugter_button, land_button;
    private TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        name = view.findViewById(R.id.name_of_player);
        tilfældigt_button = view.findViewById(R.id.tilfældigt_id);
        dyr_button = view.findViewById(R.id.dyr_id);
        frugter_button = view.findViewById(R.id.frugter_id);
        land_button = view.findViewById(R.id.land_id);



        //todo: hint navn fra firebase


        tilfældigt_button.setOnClickListener(new View.OnClickListener() {
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


        land_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GetStartL.class);
                startActivity(intent);
            }
        });






        return view;
    }
}