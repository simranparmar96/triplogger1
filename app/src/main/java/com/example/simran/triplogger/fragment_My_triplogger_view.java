package com.example.simran.triplogger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;


public class fragment_My_triplogger_view extends Fragment
{

    private Model_Mytriplogger myActivities;

    private TextView mTitleField,mDateField,mDestinationField,mDurationField,mCommentField,mLocationField,mTypeField;
    private ImageView mImageView;
    private Button mCancelBtn,mMapBtn;

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


    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.my_triplogger_view_fragment,container,false);


            mTitleField = (TextView) view.findViewById(R.id.title);
            mDateField = (TextView) view.findViewById(R.id.date);
            mDestinationField = (TextView) view.findViewById(R.id.destination);
            mDurationField = (TextView) view.findViewById(R.id.duration);
            mCommentField = (TextView) view.findViewById(R.id.comment);
            mLocationField = (TextView) view.findViewById(R.id.location);
            mTypeField = (TextView) view.findViewById(R.id.type);

            mTitleField.setText(myActivities.getTitle());
            mDateField.setText(myActivities.getDate());
            mDestinationField.setText(myActivities.getDestination());
            mDurationField.setText(myActivities.getDuration());
            mCommentField.setText(myActivities.getComment());
            mLocationField.setText(myActivities.getLocation());
            mTypeField.setText(myActivities.getType());

            mImageView = (ImageView) view.findViewById(R.id.imageView);
            mCancelBtn = (Button) view.findViewById(R.id.btn_cancel);
            mMapBtn = (Button) view.findViewById(R.id.btn_map);

            mCancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  Lab_My_Activities.get(getActivity()).deleteTrip(myActivities);
                    startActivity(triplogger_My_triplogger.newIntent(getActivity()));
                }
            });
            mMapBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    fragment_My_triplogger_view myFragment = new fragment_My_triplogger_view();

                    Bundle bundle = new Bundle();
                    bundle.putString(triplogger_My_triplogger.triplogger, myActivities.getId().toString());
                    myFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();


                }
            });

            return view;

        }
        }
