package com.example.simran.triplogger;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

 public class Lab_My_Activities {


    private static Lab_My_Activities sMyActivitiesLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private List<Model_Mytriplogger> mMyActivities;

     public static Lab_My_Activities get(Context context){
         if(sMyActivitiesLab == null){
             sMyActivitiesLab = new Lab_My_Activities(context);
         }
         return sMyActivitiesLab;
     }


     private Lab_My_Activities(Context context){

         mContext = context.getApplicationContext();
         mDatabase = new Helper_My_Activities(mContext).getWritableDatabase();

     }

     public Model_Mytriplogger addActivity(Model_Mytriplogger t){
         ContentValues values = getContentValues(t);

         mDatabase.insert(DbSchema_My_Activities.ActivityTable.NAME,null,values);
         return t;
     }


     public List<Model_Mytriplogger> getActivities(){
         List<Model_Mytriplogger> activities = new ArrayList<>();
         My_Activities_Cursor_Wrapper cursor = queryActivity(null,null);

         try {
             cursor.moveToFirst();
             while (!cursor.isAfterLast()){
                 activities.add(cursor.getActivity());
                 cursor.moveToNext();
             }
         } finally {

         }

         return activities;

     }

     public Model_Mytriplogger getActivities(UUID id){

         My_Activities_Cursor_Wrapper cursor = queryActivity(DbSchema_My_Activities.ActivityTable.Cols.UUID + " =?",new String[]{ id.toString() });

         try{
             if(cursor.getCount() == 0){
                 return null;
             }
             cursor.moveToFirst();
             return cursor.getActivity();

         } finally {
             cursor.close();
         }

     }

     public void updateTrip(Model_Mytriplogger t){

         String uuidString = t.getId().toString();
         ContentValues values = getContentValues(t);

         mDatabase.update(DbSchema_My_Activities.ActivityTable.NAME, values, DbSchema_My_Activities.ActivityTable.Cols.UUID + " =?", new String[] { uuidString });

     }

     public void deleteTrip(Model_Mytriplogger t){
         String uuidString = t.getId().toString();
         mDatabase.delete(DbSchema_My_Activities.ActivityTable.NAME, DbSchema_My_Activities.ActivityTable.Cols.UUID + " =?", new String[] { uuidString });
     }

     private static ContentValues getContentValues(Model_Mytriplogger activities){

         ContentValues values = new ContentValues();
         values.put(DbSchema_My_Activities.ActivityTable.Cols.UUID,activities.getId().toString());
         values.put(DbSchema_My_Activities.ActivityTable.Cols.TITLE,activities.getTitle());
         values.put(DbSchema_My_Activities.ActivityTable.Cols.COMMENT,activities.getComment());
         values.put(DbSchema_My_Activities.ActivityTable.Cols.DURATION,activities.getDuration());
         values.put(DbSchema_My_Activities.ActivityTable.Cols.LOCATION,activities.getLocation());
         values.put(DbSchema_My_Activities.ActivityTable.Cols.TYPE,activities.getType());
         values.put(DbSchema_My_Activities.ActivityTable.Cols.DATE,activities.getDate());
         values.put(DbSchema_My_Activities.ActivityTable.Cols.DESTINATION,activities.getDestination());

         return values;
     }

     private My_Activities_Cursor_Wrapper queryActivity(String whereClause, String[] whereArgs){
         Cursor cursor = mDatabase.query(
                 DbSchema_My_Activities.ActivityTable.NAME,
                 null,
                 whereClause,
                 whereArgs,
                 null,
                 null,
                 null
         );

         return new My_Activities_Cursor_Wrapper(cursor);
     }




 }
