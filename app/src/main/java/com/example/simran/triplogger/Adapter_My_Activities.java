package com.example.simran.triplogger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import com.example.simran.R;

import java.util.List;

public class Adapter_My_Activities extends RecyclerView.Adapter<Adapter_My_Activities.TripHolder>

{
    private Context mContext;
    private List<Model_Mytriplogger> mTrips;

    public Adapter_My_Activities(Context context, List<Model_Mytriplogger> trips){
        this.mContext = context;
        this.mTrips = trips;
    }


  public TripHolder oncreateViewTripHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_activity,parent,false);
        return new TripHolder(view);

    }


    @Override
    public TripHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public void onBindViewHolder(TripHolder holder, int position) {
        final Model_Mytriplogger myactivity =  mTrips.get(position);
        holder.bindTrip(myactivity);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                fragment_My_triplogger_view myFragment = new fragment_My_triplogger_view();

                Bundle bundle = new Bundle();
                bundle.putString(triplogger_My_triplogger.triplogger, myactivity.getId().toString());
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
            }
        });



    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public int getintcount()
    {
        return mTrips.size();
    }

    public class TripHolder extends RecyclerView. ViewHolder
    {

        private Model_Mytriplogger mTrip;

        private TextView txtTitle;
        private TextView txtDate;
        private TextView txtDesc;
        public TextView itemView;

        public TripHolder(final View itemView)
        {
            super();
            txtTitle = (TextView) itemView.findViewById(R.id.title);
            txtDate = (TextView) itemView.findViewById(R.id.date);
            txtDesc = (TextView) itemView.findViewById(R.id.desc);
        }

        public void bindTrip(Model_Mytriplogger trip){
            mTrip = trip;
            txtTitle.setText(mTrip.getTitle());
            txtDate.setText(mTrip.getDate());
            txtDesc.setText(mTrip.getDestination());
        }

    }

    public void setTrips(List<Model_Mytriplogger> trips)
    {
        mTrips = trips;
    }


}


