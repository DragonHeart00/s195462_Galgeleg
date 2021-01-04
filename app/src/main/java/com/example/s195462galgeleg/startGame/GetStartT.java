package com.example.s195462galgeleg.startGame;

import androidx.annotation.NonNull;
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
import com.example.s195462galgeleg.activities.LoserActivity;
import com.example.s195462galgeleg.activities.WinnerActivity;
import com.example.s195462galgeleg.logic.LogikT;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class GetStartT extends AppCompatActivity implements View.OnClickListener {


    LogikT logikT = new LogikT();
    private Button letterButton;
    private TextView guessTekst, show_first_char;
    private GridLayout letterGrid;
    private ImageView galgelegImage;
    private TextView playerNameText;
    private ImageButton restart,hint;
    private int count = 0;
    private int score;
    private DocumentReference documentReference;
    private FirebaseUser firebaseUser;
    //database
    private FirebaseAuth myAuth;
    private FirebaseFirestore firestore;
    private String plyerID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start_t);

        playerNameText =findViewById(R.id.player_name);
        show_first_char=findViewById(R.id.show_char);
        restart=findViewById(R.id.change_word);
        hint=findViewById(R.id.hint_word);
        myAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        firebaseUser = myAuth.getCurrentUser();

        if (firebaseUser != null) {
            //database

            plyerID = myAuth.getCurrentUser().getUid();
        }

      /*  documentReference = firestore.collection("players").document(plyerID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                int s = Integer.parseInt(value.getString("score"));
                score = s;
                Toast.makeText(getApplicationContext(),"socre" + score,Toast.LENGTH_SHORT).show();


                 }

        });

       */







        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_first_char.setText("Ordet starter med bogstav "+logikT.getOrdet().charAt(0));
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
        guessTekst.setText(logikT.getSynligtOrd());
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
        logikT.gætBogstav(BogstavGæt);


        galgelegImage.setVisibility(galgelegImage.VISIBLE);

        guessTekst.setText(logikT.getSynligtOrd());
        letterButton.setVisibility(View.GONE);


        switch (logikT.getAntalForkerteBogstaver()){

            case 1:
                galgelegImage.setImageResource(R.drawable.galge);
                break;
            case 2:
                galgelegImage.setImageResource(R.drawable.forkert1);
                break;
            case 3:
                galgelegImage.setImageResource(R.drawable.forkert2);
                break;
            case 4:
                galgelegImage.setImageResource(R.drawable.forkert3);
                break;
            case 5:
                galgelegImage.setImageResource(R.drawable.forkert4);
                break;
            case 6:
                galgelegImage.setImageResource(R.drawable.forkert5);
                break;
            case 7:
                galgelegImage.setImageResource(R.drawable.forkert6);
                break;
            default:
                galgelegImage.setImageResource(R.drawable.galge);
                galgelegImage.setVisibility(galgelegImage.INVISIBLE);
                break;
        }

        if (logikT.erSpilletVundet()) {
            //Send user to WinnerActivity


            if (logikT.getAntalForkerteBogstaver() == 0){
                score= 400;
                score+=score * 2 + logikT.getOrdet().length();

            }else {
                score+=200;
                score+= logikT.getOrdet().length() * logikT.getAntalForkerteBogstaver();

            }
            if (firebaseUser != null) {
                //database

                documentReference = firestore.collection("players").document(plyerID);
                Map<String, Object> players = new HashMap<>();
                players.put("score", score);

                documentReference.update(players).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
            Toast.makeText(getApplicationContext(),"score:" +score,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, WinnerActivity.class);
            intent.putExtra("AntalForkerteBogstaver",logikT.getAntalForkerteBogstaver()+"");
            intent.putExtra("yScore",score);
            startActivity(intent);
            finish();





        } else if (logikT.erSpilletTabt()){
            //Send user to LoserActivity
            score+= logikT.getOrdet().length() + logikT.getAntalForkerteBogstaver();


            if (firebaseUser != null) {
                //database

                documentReference = firestore.collection("players").document(plyerID);
                Map<String, Object> players = new HashMap<>();
                players.put("score", score);

                documentReference.update(players).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }


            Toast.makeText(getApplicationContext(),logikT.getOrdet() + "\n score:" +score,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoserActivity.class);
            intent.putExtra("data",logikT.getOrdet());
            intent.putExtra("yScore",score +"");
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
