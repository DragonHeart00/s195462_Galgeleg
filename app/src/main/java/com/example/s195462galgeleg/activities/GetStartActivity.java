package com.example.s195462galgeleg.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.logic.Galgelogik;

public class GetStartActivity extends AppCompatActivity implements View.OnClickListener {


    Galgelogik galgelogik = new Galgelogik();
    private Button letterButton;
    private TextView guessTekst, show_first_char;
    private GridLayout letterGrid;
    private ImageView galgelegImage;
    private TextView playerNameText;
    private ImageButton restart,hint;
    private int count = 0;
    private int score = 200;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);

        playerNameText =findViewById(R.id.player_name);
        show_first_char=findViewById(R.id.show_char);
        restart=findViewById(R.id.change_word);
        hint=findViewById(R.id.hint_word);


        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_first_char.setText("Ordet starter med bogstav "+galgelogik.getOrdet().charAt(0));
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmRestGame();
            }
        });


        Intent intent=getIntent();
        String playerName = intent.getStringExtra("name");
        playerNameText.setText(playerName);

        guessTekst = findViewById(R.id.guss);
        letterGrid = findViewById(R.id.gridLayout);
        galgelegImage = findViewById(R.id.imageView);



        // guss word ******
        guessTekst.setText(galgelogik.getSynligtOrd());
        galgelegImage.setVisibility(galgelegImage.INVISIBLE);

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


        galgelegImage.setVisibility(galgelegImage.VISIBLE);

        guessTekst.setText(galgelogik.getSynligtOrd());
        letterButton.setVisibility(View.GONE);


        switch (galgelogik.getAntalForkerteBogstaver()){

            case 1:
                galgelegImage.setImageResource(R.drawable.galge);
                score = 150;
                break;
            case 2:
                galgelegImage.setImageResource(R.drawable.forkert1);
                score = 100;
                break;
            case 3:
                galgelegImage.setImageResource(R.drawable.forkert2);
                score = 50;
                break;
            case 4:
                galgelegImage.setImageResource(R.drawable.forkert3);
                score = 25;
                break;
            case 5:
                galgelegImage.setImageResource(R.drawable.forkert4);
                score = 10;
                break;
            case 6:
                galgelegImage.setImageResource(R.drawable.forkert5);
                score = 15;
                break;
            case 7:
                galgelegImage.setImageResource(R.drawable.forkert6);
                score = 5;
                break;
            default:
                galgelegImage.setImageResource(R.drawable.galge);
                galgelegImage.setVisibility(galgelegImage.INVISIBLE);
                break;
        }

        if (galgelogik.erSpilletVundet()) {
            //Send user to WinnerActivity
            Intent intent = new Intent(this, WinnerActivity.class);
            intent.putExtra("AntalForkerteBogstaver",galgelogik.getAntalForkerteBogstaver()+"");
            intent.putExtra("your_score","score:"+ score +"");
            startActivity(intent);
            finish();


        } else if (galgelogik.erSpilletTabt()){
            //Send user to LoserActivity
            Toast.makeText(getApplicationContext(),galgelogik.getOrdet(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoserActivity.class);
            intent.putExtra("data",galgelogik.getOrdet());
            intent.putExtra("your_score","score:"+ score +"");
            startActivity(intent);
            finish();


        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Afslutning")
                .setMessage("Er du sikker på, at du vil annullere spillet?")
                .setPositiveButton("Ja", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }

                })
                .setNegativeButton("Fortsæt", null)
                .show();
    }


    private void confirmRestGame() {

        new AlertDialog.Builder(this)
                .setTitle("Nyt Ordet")
                .setMessage("Få et nyt ord")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Nej", null)
                .show();



    }

}
