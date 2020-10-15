package com.example.s195462galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.s195462galgeleg.R;
import com.example.s195462galgeleg.logic.Galgelogik;

public class GetStartActivity extends AppCompatActivity implements View.OnClickListener {


    //----------------test-----------------//
    Galgelogik galgelogik = new Galgelogik();
    Button letterButton;
    TextView gætteTekst, forkerteBogstaver;
    GridLayout letterGrid, letterGrid2;
    ImageView gallow;
    long startTime, endTime;
    int totalTime, score;
    int count = 0;
    //-------------------------------------//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);


        gætteTekst = findViewById(R.id.guss);
        forkerteBogstaver = findViewById(R.id.textView4);
        letterGrid = findViewById(R.id.gridLayout);
        gallow = findViewById(R.id.imageView);
        gallow.setVisibility(gallow.INVISIBLE);

        gætteTekst.setText(galgelogik.getSynligtOrd());
        System.out.println("onCreate: Ordet er: " + galgelogik.getOrdet());
        //Start timer
        startTime = System.currentTimeMillis();




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
        forkerteBogstaver.setText("Forkerte bogstaver ["
                + galgelogik.getAntalForkerteBogstaver()
                + " ud af 7]: \n"
                + galgelogik.wrongLetters(galgelogik.getBrugteBogstaver().toString(), galgelogik.getOrdet()));
        gætteTekst.setText(galgelogik.getSynligtOrd());
        letterButton.setVisibility(View.GONE);

        //Update the gallow and stick man
        gallow.setVisibility(gallow.VISIBLE);

        switch (galgelogik.getAntalForkerteBogstaver()){
            case 1:
                gallow.setImageResource(R.drawable.galge);
                break;
            case 2:
                gallow.setImageResource(R.drawable.forkert1);
                break;
            case 3:
                gallow.setImageResource(R.drawable.forkert2);
                break;
            case 4:
                gallow.setImageResource(R.drawable.forkert3);
                break;
            case 5:
                gallow.setImageResource(R.drawable.forkert4);
                break;
            case 6:
                gallow.setImageResource(R.drawable.forkert5);
                break;
            case 7:
                gallow.setImageResource(R.drawable.forkert6);
                break;
            default:
                gallow.setImageResource(R.drawable.galge);
                gallow.setVisibility(gallow.INVISIBLE);
                break;
        }

        //Current end-game
        galgelogik.logStatus();
        if (galgelogik.erSpilletVundet()) {
            endTime = System.currentTimeMillis();
            totalTime = Math.round(((endTime - startTime)/1000));
            System.out.println("Det tog " + (totalTime) + " sekunder");

            //Calculate score
            int letterPenalty = galgelogik.getAntalForkerteBogstaver()*5000;
            System.out.println("letterPenalty: " + letterPenalty);
            int timePenalty = (totalTime*10000)/galgelogik.getOrdet().length();
            System.out.println("timePenalty: " + timePenalty);
            score = (100000 - letterPenalty - timePenalty);
            System.out.println("Scoren er: " + score);
            if (score < 0) {
                score = 0;
                score += galgelogik.getOrdet().length()*1000;
            }

           /* //Send data to wonActivity
            Intent i = new Intent(this, WonActivity.class);
            i.putExtra("ordet", galgelogik.getOrdet());
            i.putExtra("transferCount", count);
            i.putExtra("time", totalTime);
            i.putExtra("score", score);
            startActivity(i);
            finish();

            */
        } else if (galgelogik.erSpilletTabt()){
            //Send data to lostActivity
      /*      Intent i = new Intent(this, LostActivity.class);
            i.putExtra("ordet", galgelogik.getOrdet());
            startActivity(i);
            finish();

       */
        }
    }





}
