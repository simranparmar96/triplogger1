package com.example.simran.triplogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;

import com.example.simran.R;

import java.util.List;

public class Fragment_My_triplogger_List extends Fragment
{
    private RecyclerView mTripRecyclerView;
    private Adapter_My_Activities mAdapter;

    private Button mLogButton;
    private Button mSettingsButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.triplogger_list_fragment,container,false);

        mTripRecyclerView = (RecyclerView) view.findViewById(R.id.trip_recycler_view);
        mTripRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mLogButton = (Button) view.findViewById(R.id.btn_log);
        mSettingsButton = (Button) view.findViewById(R.id.btn_settings);

        mLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Model_Mytriplogger a = new Model_Mytriplogger();
                Lab_My_Activities.get(getActivity()).addActivity(a);
                Intent intent = triplogger_My_triplogger.newIntent(getActivity(), a.getId());
                startActivity(intent);

            }
        });

        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = triplogger_settings.newIntent(getActivity());
                startActivity(intent);

            }
        });

        updateUI();

        return view;

    }

    private void updateUI() {
        Lab_My_Activities triplab = Lab_My_Activities.get(getActivity());
        List<Model_Mytriplogger> trips = triplab.getActivities();

        if
                (mAdapter == null)
        {
            mAdapter = new Adapter_My_Activities(getActivity(), trips);
            mTripRecyclerView.setAdapter(mAdapter);
        }

        else
            {
            mAdapter.setTrips(trips);
            mAdapter.notifyDataSetChanged();
        }

    }

      public void onResume(){
        super.onResume();
        updateUI();
      }

    }


