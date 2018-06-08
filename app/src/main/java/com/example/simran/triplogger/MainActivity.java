package com.example.simran.triplogger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.simran.R;


public class MainActivity extends AppCompatActivity {



    public static final String triplogger = "triplogger_My_triplogger";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null){
            fragment = new Fragment_My_triplogger_List( );
            fm.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }

    }

    public static Intent newIntent(Context packageContent){

        Intent intent = new Intent(packageContent, triplogger_My_triplogger.class);
        return intent;

    }

    }

