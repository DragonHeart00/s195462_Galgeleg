package com.example.s195462galgeleg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import android.content.DialogInterface;
import android.os.Bundle;
import com.example.s195462galgeleg.controller.ViewPagerAdapter;
import com.example.s195462galgeleg.fragments.GuestFragment;
import com.example.s195462galgeleg.fragments.RuleView;
import com.example.s195462galgeleg.fragments.HomeView;
import com.example.s195462galgeleg.fragments.ScoreView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class MainActivity extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Toolbar toolbar = findViewById(R.id.tool_bar);
       setSupportActionBar(toolbar);
       firebaseAuth=FirebaseAuth.getInstance();
       firebaseUser = firebaseAuth.getCurrentUser();


    }


    @Override
    protected void onStart() {
        super.onStart();
        setAdapter();
    }

    private void setAdapter() {
        // Tabbed Activity
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_page);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //ViewPagerAdapter
        viewPagerAdapter.addFragment(new RuleView(), "Rules");
        viewPagerAdapter.addFragment(new HomeView(), "Home");

        tabLayout.setupWithViewPager(viewPager);





        if (firebaseUser == null){
            viewPagerAdapter.addFragment(new GuestFragment(), "new");
            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_rule);
            tabLayout.getTabAt(1).setIcon(R.drawable.ic_home);
            tabLayout.getTabAt(2).setIcon(R.drawable.ic_person);

        }else {
            viewPagerAdapter.addFragment(new ScoreView(), "Score");
            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_rule);
            tabLayout.getTabAt(1).setIcon(R.drawable.ic_home);
            tabLayout.getTabAt(2).setIcon(R.drawable.ic_person);
        }
        //run "home fragment" first
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onBackPressed() {
        goBack();
    }

    private void goBack(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Afslutning")
                .setMessage("Er du sikker på, at du vil vende tilbage til velkomstsiden?")
                .setCancelable(false)
                .setPositiveButton("Ja", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }

                })
                .setNegativeButton("Nej", null)
                .show();
    }
}