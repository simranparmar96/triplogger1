package com.example.simran.triplogger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.simran.R;

import java.util.UUID;


public class triplogger_My_triplogger extends AppCompatActivity
{
    public static final String triplogger = "triplogger_My_triplogger";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null){
            fragment = new Fragment_My_triplogger();
            fm.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();

        }
}

    public static Intent newIntent(Context packageContent, UUID tripID){

        Intent intent = new Intent(packageContent, triplogger_My_triplogger.class);
        intent.putExtra(triplogger, tripID);
        return intent;
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
