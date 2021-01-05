package com.example.s195462galgeleg.startGame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.s195462galgeleg.logic.LogicD;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class GetStartD extends AppCompatActivity implements View.OnClickListener {


    LogicD logicD = new LogicD();
    private Button letterButton;
    private TextView guessTekst, show_first_char;
    private GridLayout letterGrid;
    private ImageView galgelegImage;
    private ImageButton restart,hint;
    private int count = 0;
    private int score;
    private DocumentReference documentReference;
    private FirebaseUser firebaseUser;
    //database
    private FirebaseAuth myAuth;
    private FirebaseFirestore firestore;
    private String plyerID;

    //name and score
    private TextView playerNameText, score_text;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start_t);

        playerNameText =findViewById(R.id.player_name);
        score_text=findViewById(R.id.txt);
        show_first_char=findViewById(R.id.show_char);
        restart=findViewById(R.id.change_word);
        hint=findViewById(R.id.hint_word);
        myAuth =FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        firebaseUser = myAuth.getCurrentUser();




        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_first_char.setText("Ordet starter med bogstav "+ logicD.getOrdet().charAt(0));
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
        guessTekst.setText(logicD.getSynligtOrd());
        galgelegImage.setVisibility(galgelegImage.INVISIBLE);



        if (firebaseUser != null) {
            //database

            plyerID = myAuth.getCurrentUser().getUid();


            DocumentReference documentReference = firestore.collection("players").document(plyerID);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                    playerNameText.setText(value.getString("name"));

//                    int x = (int)  value.get("score");
//                    score_text.setText( x + "");
                }
            });


        }


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
        logicD.gætBogstav(BogstavGæt);


        galgelegImage.setVisibility(galgelegImage.VISIBLE);

        guessTekst.setText(logicD.getSynligtOrd());
        letterButton.setVisibility(View.GONE);


        switch (logicD.getAntalForkerteBogstaver()){

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

        if (logicD.erSpilletVundet()) {
            //Send user to WinnerActivity

            if (logicD.getAntalForkerteBogstaver() == 0){
                score= 400;
                score+=score * 2 + logicD.getOrdet().length();

            }else {
                score+=200;
                score+= logicD.getOrdet().length() * logicD.getAntalForkerteBogstaver();

            }
            if (firebaseUser != null) {
                //database

                documentReference = firestore.collection("players").document(plyerID);
                Map<String, Object> players = new HashMap<>();
                players.put("score", score +"");

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
            intent.putExtra("AntalForkerteBogstaver", logicD.getAntalForkerteBogstaver()+"");
            intent.putExtra("yScore",score);
            startActivity(intent);
            finish();

        } else if (logicD.erSpilletTabt()){
            //Send user to LoserActivity
            score+= logicD.getOrdet().length() + logicD.getAntalForkerteBogstaver();


            if (firebaseUser != null) {
                //database

                documentReference = firestore.collection("players").document(plyerID);
                Map<String, Object> players = new HashMap<>();
                players.put("score", score + "");

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



            Intent intent = new Intent(this, LoserActivity.class);
            intent.putExtra("data", logicD.getOrdet());
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