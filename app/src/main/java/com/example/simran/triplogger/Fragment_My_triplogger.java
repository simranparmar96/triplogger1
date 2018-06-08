package com.example.simran.triplogger;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.simran.R;

import java.util.UUID;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LOCATION_SERVICE;

public class Fragment_My_triplogger extends Fragment implements GoogleApiClient.connectioncallbacks
 {
     private Model_Mytriplogger myActivities;

     private EditText mTitleField,mDateField,mDestinationField,mDurationField,mCommentField;
     private Spinner mType;
     private TextView mLocationField;
     private Button mSaveBtn,mCancelBtn,mCameraBtn;
     private ImageView mImageView;
     String tripID;
     private GoogleApiClient mClient;


     static final int REQUEST_IMAGE_CAPTURE = 1;

     private void dispatchTakePictureIntent() {
         Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
             this.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
         }
     }


     @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

         if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

             Bundle extras = data.getExtras();
             Bitmap imageBitmap = (Bitmap) extras.get("data");
             Toast.makeText(getActivity(),imageBitmap.getHeight()+"",Toast.LENGTH_LONG).show();
             mImageView.setImageBitmap(imageBitmap);
         }
     }


     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         UUID tripID = (UUID) getActivity().getIntent().getSerializableExtra(triplogger_My_triplogger.triplogger);
         myActivities = Lab_My_Activities.get(getActivity()).getActivities(tripID);

         mClient = new GoogleApiClient.Builder(getActivity()).addApi(LOCATIONSERVICES.API).addConnectionCallbacks(this).build();
     }

         @Override
         public View onCreateView(LayoutInflater inflater, ViewGroup container,
                 Bundle savedInstanceState) {
             View view = inflater.inflate(R.layout.activity_fragment,container,false);

             mTitleField = (EditText) view.findViewById(R.id.activities_title);
             mDateField = (EditText) view.findViewById(R.id.acitivites_date);
             mDestinationField = (EditText) view.findViewById(R.id.acitivites_destination);
             mDurationField = (EditText) view.findViewById(R.id.acitivites_duration);
             mCommentField = (EditText) view.findViewById(R.id.acitivites_comment);
             mLocationField = (TextView) view.findViewById(R.id.acitivites_location);

             mType = (Spinner) view.findViewById(R.id.acitivites_type);

             mImageView = (ImageView) view.findViewById(R.id.imageView);

             mLocationField.setText("Location Location...");

             mSaveBtn = (Button) view.findViewById(R.id.btn_save);
             mCancelBtn = (Button) view.findViewById(R.id.btn_cancel);
             mCameraBtn = (Button) view.findViewById(R.id.btn_camera);

             mCameraBtn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     dispatchTakePictureIntent();
                 }
             });


             mTitleField.addTextChangedListener(new TextWatcher() {
                 @Override
                 public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                 }

                 @Override
                 public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                     myActivities.setTitle(s.toString());
                 }

                 @Override
                 public void afterTextChanged(Editable editable) {

                 }
             });

             mDateField.addTextChangedListener(new TextWatcher() {
                 @Override
                 public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                 }

                 @Override
                 public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                     myActivities.setDate(s.toString());
                 }

                 @Override
                 public void afterTextChanged(Editable editable) {

                 }
             });

             mDurationField.addTextChangedListener(new TextWatcher() {
                 @Override
                 public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                 }

                 @Override
                 public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                     myActivities.setDuration(s.toString());
                 }

                 @Override
                 public void afterTextChanged(Editable editable) {

                 }
             });

             mDestinationField.addTextChangedListener(new TextWatcher() {
                 @Override
                 public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                 }

                 @Override
                 public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                     myActivities.setDestination(s.toString());
                 }

                 @Override
                 public void afterTextChanged(Editable editable) {

                 }
             });

             mCommentField.addTextChangedListener(new TextWatcher() {
                 @Override
                 public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                 }

                 @Override
                 public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                     myActivities.setComment(s.toString());
                 }

                 @Override
                 public void afterTextChanged(Editable editable) {

                 }
             });

             mSaveBtn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Lab_My_Activities.get(getActivity()).updateTrip(myActivities);
                     startActivity(MainActivity.newIntent(getActivity()));
                 }
             });

             mCancelBtn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Lab_My_Activities.get(getActivity()).deleteTrip(myActivities);
                     startActivity(MainActivity.newIntent(getActivity()));
                 }
             });

             return view;

         }

     @Override
     public void onStart(){
         super.onStart();
         mClient.connect();
     }

     @Override
     public void onPause(){
         super.onPause();
         myActivities.setType(mType.getSelectedItem().toString());
         Lab_My_Activities.get(getActivity()).updateTrip(myActivities);
     }

     @Override
     public void onStop() {
         super.onStop();
         mClient.disconnect();
     }

     @Override
     public void onConnected ( Bundle bundle) {
         LocationRequest = new LocationRequest().create();
         request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
         request.setNumUpdates(1);
         request.setInterval(0);

         if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
             mLocationField.setText("No Permission");
             return;
         }

         LocationServices.FusedLocationApi.requestLocationUpdates(mClient, request, new LocationListener() {
             @Override
             public void onLocationChanged(Location location) {
                 mLocationField.setText(location.getLatitude()+","+location.getLongitude());
                 myActivities.setLocation(location.getLatitude()+","+location.getLongitude());
             }
         });


     }

     @Override
     public void onConnectionSuspended(int i) {

     }
}
