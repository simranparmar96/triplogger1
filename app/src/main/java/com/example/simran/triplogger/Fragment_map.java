package com.example.simran.triplogger;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simran.R;

import java.util.UUID;

public class Fragment_map extends Fragment {


    private Model_Mytriplogger myActivities;
    GoogleMap googleMap;
    LatLng myPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String tripID = "";

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            tripID = bundle.getString(triplogger_My_triplogger.triplogger, "");
        }
        myActivities = Lab_My_Activities.get(getActivity()).getActivities(UUID.fromString(tripID));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment,container,false);



        return view;

    }

}
