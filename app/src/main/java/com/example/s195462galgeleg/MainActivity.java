package com.example.s195462galgeleg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.s195462galgeleg.controller.ViewPagerAdapter;
import com.example.s195462galgeleg.fragments.RuleView;
import com.example.s195462galgeleg.fragments.HomeView;
import com.example.s195462galgeleg.fragments.ScoreView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Toolbar toolbar = findViewById(R.id.tool_bar);
       setSupportActionBar(toolbar);


        // Tabbed Activity
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_page);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //ViewPagerAdapter
        viewPagerAdapter.addFragment(new RuleView(), "Rules");
        viewPagerAdapter.addFragment(new HomeView(), "Home");
        viewPagerAdapter.addFragment(new ScoreView(), "Score");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        //run "home fragment" first
        viewPager.setCurrentItem(1);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_rule);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_person);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Afslutning")
                .setMessage("Er du sikker på, at du vil vende tilbage til velkomstsiden?")
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