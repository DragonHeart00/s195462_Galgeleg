package com.example.s195462galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.logic.Galgelogik;

public class GetStartActivity extends AppCompatActivity implements View.OnClickListener {


    Galgelogik galgelogik = new Galgelogik();
    private Button letterButton;
    private TextView gussTekst;
    private GridLayout letterGrid;
    private ImageView galgelegImage;
    private int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);


        gussTekst = findViewById(R.id.guss);
        letterGrid = findViewById(R.id.gridLayout);
        galgelegImage = findViewById(R.id.imageView);



        // guss word ******
        gussTekst.setText(galgelogik.getSynligtOrd());

    }


    @Override
    public void onClick(View view) {
        letterButton = findViewById(view.getId());
        myOnClick(letterButton);
        count++;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private void myOnClick(Button letterButton) {

        String BogstavGæt = (letterButton.getText().toString().toLowerCase());

        //Calls to the logic.
        galgelogik.gætBogstav(BogstavGæt);


        gussTekst.setText(galgelogik.getSynligtOrd());
        letterButton.setVisibility(View.GONE);


        switch (galgelogik.getAntalForkerteBogstaver()){

            case 1:
                galgelegImage.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                galgelegImage.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                galgelegImage.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                galgelegImage.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                galgelegImage.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                galgelegImage.setImageResource(R.drawable.forkert6);
                break;
            default:
                galgelegImage.setImageResource(R.drawable.galge);
                galgelegImage.setVisibility(galgelegImage.INVISIBLE);
                break;
        }

        if (galgelogik.erSpilletVundet()) {
            //Send user to WinnerActivity
            Intent intent = new Intent(this, WinnerActivity.class);
            startActivity(intent);
            finish();


        } else if (galgelogik.erSpilletTabt()){
            //Send user to LoserActivity
            Intent intent = new Intent(this, LoserActivity.class);
            startActivity(intent);
            finish();


        }
    }





}
