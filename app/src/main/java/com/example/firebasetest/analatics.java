package com.example.firebasetest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class analatics extends AppCompatActivity {
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference user = myRef.child("users");
    DatabaseReference firebase_data_air =user.child("niluksha").child("Analytics").child("Air");
    DatabaseReference firebase_data_sound =user.child("niluksha").child("Analytics").child("Sound");
    GraphView graphAir,graphSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analatics);
        graphAir = (GraphView) findViewById(R.id.graphair);
        graphSound = (GraphView) findViewById(R.id.graphsound);
        graphAir.getViewport().setMaxX(30);
        graphAir.getViewport().setScalable(true);
        graphSound.getViewport().setMaxX(30);
        graphSound.getViewport().setScalable(true);

        getdata();
    }
    int dayAir=0,daySound=0;

    public void getdata(){
        // Read from the database
        firebase_data_air.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {

                });
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    dayAir++;
                    float value = data.getValue(Float.class);
                    series.appendData(new DataPoint(dayAir,value),true,dayAir);
                }
                graphAir.addSeries(series);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });


        // Read Sound Value from the database
        firebase_data_sound.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {

                });
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    daySound++;
                    float value = data.getValue(Float.class);
                    series.appendData(new DataPoint(daySound,value),true,daySound);
                }
                graphSound.addSeries(series);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });
    }

}
