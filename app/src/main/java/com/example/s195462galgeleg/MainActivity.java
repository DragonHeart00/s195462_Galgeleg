package com.example.s195462galgeleg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.s195462galgeleg.controller.ViewPagerAdapter;
import com.example.s195462galgeleg.fragments.RuleFragment;
import com.example.s195462galgeleg.fragments.HomeFragment;
import com.example.s195462galgeleg.fragments.ScoreFragment;
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
       getSupportActionBar().setTitle(getString(R.string.app_name));
     //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Tabbed Activity
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_page);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //ViewPagerAdapter
        viewPagerAdapter.addFragment(new RuleFragment(), "Rules");
        viewPagerAdapter.addFragment(new HomeFragment(), "Home");
        viewPagerAdapter.addFragment(new ScoreFragment(), "Score");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        //run "home fragment" first
        viewPager.setCurrentItem(1);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_rule);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_person);
    }
}